package Interview.twosigma.atm;

public class ATM {
	
	private Screen screen;
	private Keypad keypad;
	private CashDispenser cashDispenser;
	private DepositSlot depositSlot;
	private BankDatabase bankDatabase;
	
	private ApiConnect apiconnect;
	
	private boolean userAuthenticated;
	private int currentAccountNumber;
	private Session userSession;
	
	private static final int BALANCE_INQUIRY = 1;
	private static final int WITHDRAWAL = 2;
	private static final int DEPOSIT = 3;
	private static final int EXIT = 4;
	
	private static final int CREATE = 5;
	private static final int LOGIN = 6;
	
	
	public ATM() {
		userAuthenticated = false;
		currentAccountNumber = 0;
		
		screen = new Screen();
		keypad = new Keypad();
		cashDispenser = new CashDispenser();
		depositSlot = new DepositSlot();
		bankDatabase = new BankDatabase();
	}
	
	public void run() {
		while (true) {
			while (!userAuthenticated) {
				performWelcomPage();
				authenticateUser();
			}
			
			performTransactions();
			userAuthenticated = false;
			currentAccountNumber = 0;
			screen.display("\nThank you, bye");
		}
	}
	
	void performWelcomPage() {
		screen.display("Welcom");
		boolean userExited = false;
		
		while (!userExited) {
			int userSelect = 1;
			switch(userSelect) {
				case CREATE:
					createAccount();
					break;
				case LOGIN:
					authenticateUser();
					break;
				case EXIT:
					userExited = true;
					break;
				default:
					screen.display("\n not a valid selection, try again");	
					break;
			}
		}
	}
	
	private void createAccount() {
		
	}
	
	private void authenticateUser() {
		int attempts = 0;
		int accountNumber = 0;
		
		while (!userAuthenticated && attempts < 5) {
			screen.display("\nPlease enter your account number: ");
			accountNumber = keypad.getInput();
			screen.display("\nEnter your pin");
			int pin = keypad.getInput();
			
			userAuthenticated = apiconnect.authenticate(accountNumber, pin);
			
			if (!userAuthenticated) {
				attempts++;
				screen.display("\n Invalid please try again ");
			}
		}
		
		if (!userAuthenticated) {
			apiconnect.lock(accountNumber);
			screen.display("\n Account is locked ");
			return;
		} else {
			currentAccountNumber = accountNumber;
			userSession = apiconnect.createSession(accountNumber);
			screen.display("\n login success, will redirect...");
		}
	}
	
	private void performTransactions() {
		Transaction currentTransaction = null;
		boolean userExited = false;
		
		while (!userExited) {
			int mainMenuSelection = displayMainMenu();
			
			switch(mainMenuSelection) {
				case BALANCE_INQUIRY:
				case WITHDRAWAL:
				case DEPOSIT:
					currentTransaction = createTransaction(mainMenuSelection);
					currentTransaction.execute();
					break;
				case EXIT:
					userExited = true;
					break;
				default:
					screen.display("\n not a valid selection, try again");	
					break;
			}
		}
	}
	
	private int displayMainMenu() {
		return keypad.getInput();
	}
	
	Transaction createTransaction(int type) {
		Transaction tmp = null;
		switch(type) {
		case BALANCE_INQUIRY:
			tmp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
			break;
		case WITHDRAWAL:
			tmp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
			break;
		case DEPOSIT:
			tmp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
			break;
		}
		return tmp;
	}
}
