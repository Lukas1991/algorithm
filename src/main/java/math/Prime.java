package math;

public class Prime {

	/**
	 * @param args
	 */
	/**
	 * 2 is prime, 1 is not prime
	 * only have to go up to the square root of n, 
	 * because if you list out all of the factors of a number, the square root will always be in the middle 
	 */
	public static boolean isPrime(int n){
		if(n==2) return true;
		if(n%2 == 0) return false;
	
		//check all odd numbers3,5,7,9, to square root
		for(int i=3; i*i<=n; i+=2){
			if(n%i == 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
