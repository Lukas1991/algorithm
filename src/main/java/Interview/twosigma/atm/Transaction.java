package Interview.twosigma.atm;

public abstract class Transaction {
	int accountNumber;
	Screen screen;
	BankDatabase bankDatabase;
	Account account;
	AccountStore accountStore;
	
	abstract public void execute();
}
