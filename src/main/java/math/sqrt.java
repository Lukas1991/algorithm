package math;

public class sqrt {

	/**
	 * One thing to note is that we have to use long for mid to avoid mid*mid
	 * from overflow. Also, you can use long type for low and high to avoid type
	 * casting for mid from long to int.
	 */
	public int mySqrt(int x) {
		if (x == 0)
			return 0;
		if (x < 0)
			return -1;

		int left = 1, right = x;
		while (left <= right) {
			long mid = left + (right - left) / 2; // (left + right) may overflow
			long a = mid * mid; // mid has to be long
			if (a == x) {
				return (int) mid;
			} else if (a > x) {
				right = (int) (mid - 1);
			} else {
				left = (int) (mid + 1);
			}
		}
		return right;
	}

	public boolean isPerfectSquare(int num) {
		long left = 1;
		long right = num;
		while (left <= right) {
			long mid = left + (right - left) / 2;
			long a = mid * mid;
			if (a == num) {
				return true;
			} else if (a > num) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return false;
	}

	//Xk+1 = (Xk + n/Xk) / 2
	public int sqrtNewton(int x) {
		if (x == 0)
			return 0;
		
		long r = x;
	    while (r*r > x) {
	    	 r = (r + x/r) / 2;
	    }
	       
	    return (int) r;
	}

	public int sqrt(int x) {
		int i = 0;
		int j = x / 2 + 1;
		while (i <= j) {
			double mid = (i + j) / 2;
			double r = mid * mid;
			// System.out.println("i is: "+i+" j is: "+j+" mid is: "+mid+" r is:
			// "+r);
			if (r == x)
				return (int) mid;
			if (r < x)
				i = (int) mid + 1;
			if (r > x)
				j = (int) mid - 1;
		}
		return j;
	}

	public static void main(String[] args) {
		sqrt s = new sqrt();
		// int result = s.sqrt(10);
		// int result = s.mySqrt(1);
		// int result = s.sqrt(100000000);
		// System.out.println(result);
		
		//test integer overflow
		System.out.println(2147483600 + 2147483600);
		System.out.println(2147483600 * 2147483600);
		
		long a = 2147483600;
		long i = a * a;
		System.out.println(i);
	}

}