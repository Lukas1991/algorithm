package Interview.twosigma.atm;

public class Deposit extends Transaction {
	Keypad keypad;
	DepositSlot depositSlot;
	private final static int CANCEL = 0;
	
	public Deposit(int accountNumber, Screen screen, BankDatabase bankDatabase, Keypad keypad, DepositSlot depositSlot) {
		this.accountNumber = accountNumber;
		this.screen = screen;
		this.bankDatabase = bankDatabase;
	}
	
	public void execute() {
		double amount = promptForDepositAmount();
		if (amount != CANCEL) {
			screen.display("please insert bills");
			
			boolean envelopeReceived = depositSlot.isReceived();
			if (envelopeReceived) {
				account.credit(amount);
				accountStore.update(account);
			} else {
				screen.display("cancel your transaction");
			}
		} else {
			screen.display("cancel your transaction");
		}
	}
	
	double promptForDepositAmount() {
		screen.display("enter amount");
		int input = keypad.getInput();
		if (input == CANCEL) {
			return CANCEL;
		} else {
			return input/100.0;
		}
		
	}
}
