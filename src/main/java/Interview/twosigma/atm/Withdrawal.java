package Interview.twosigma.atm;

public class Withdrawal extends Transaction {

	Keypad keypad;
	CashDispenser cashDispenser;
	
	public Withdrawal(int accountNumber, Screen screen, BankDatabase bankDatabase, Keypad keypad, CashDispenser cashDispenser) {
		this.accountNumber = accountNumber;
		this.screen = screen;
		this.bankDatabase = bankDatabase;
	}
	
	public void execute() {
		
	}
}
