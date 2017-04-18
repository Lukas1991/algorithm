package math;

public class Power {


	/**O(logn)
	 * @param args
	 */
	public double pow(double x, int n) {
		if(n==0) return 1;
		if(n<0){
			return 1/value(x,-n);
		}
		return value(x,n);

	}
 
    public double value(double x, int n) {
		if(n==1) return x;
		double pow = pow(x, n/2);
		//if even
		if(n%2 ==0){
			return pow*pow;
		}else{//odd
			return pow*pow*x;
		}

    }
		    
	public double pow2(double x, int n) {
		double res = 1;
		for(int i=1; i<=n ;i++){
			res = res * x;
		}

		return res;
	}

	public boolean isPowerOfThree(int n) {
		if (n <= 0) {
			return false;
		}
		while (n != 1) {
			if (n % 3 == 0) {
				n = n / 3;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * the maximum value of n that is also a power of three is 1162261467 Therefore, the possible values of n where we should return
	 * true are 3^0, 3^1 ... 3^19​, Since 3 is a prime number, the only divisors of 3^19​ are 3^0, 3^1 ... 3^19​, therefore all we
	 * need to do is divide 3^19​​ by n. A remainder of 0 means n is a divisor of 3^19​, and therefore a power of three.
	 */
	public boolean isPowerOfThreeBest(int n) {
		return n > 0 && 1162261467 % n == 0;
	}

	public static int getMaxNumberPowerOfThree() {
		int i = 0;
		int max = 1;
		int maxThree = Integer.MAX_VALUE / 3;
		while (max < maxThree) {
			max = 3 * max;
			i++;
		}
		return max;
	}

	public static void main(String[] args) {
		System.err.println(getMaxNumberPowerOfThree());  //1162261467
	}
}
