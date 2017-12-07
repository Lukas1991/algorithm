package Interview.twosigma.atm;

public class Account {
	int accountNumber;
	int pin;
	double availableBalance;
	double totalBalance;
	
	public void credit(double amount) {
		totalBalance += amount;
	}
	
	public void debit(double amount) {
		availableBalance -= amount;
		totalBalance -= amount;
	}
}
