package Interview.twosigma.atm;

public interface ApiConnect {

	
	boolean authenticate(int account, int pin);
	
	Session createSession(int account);
	
	void lock(int account);
}
