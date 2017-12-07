package Interview.twosigma.atm;

public class BalanceInquiry extends Transaction {
	
	int account;
	Screen screen;
	BankDatabase bankDatabase;
	
	public BalanceInquiry(int account, Screen screen, BankDatabase bankDatabase) {
		this.account = account;
		this.screen = screen;
		this.bankDatabase = bankDatabase;
	}

	public void execute() {
		
	}
}
