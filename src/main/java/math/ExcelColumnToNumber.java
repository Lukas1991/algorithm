package math;

public class ExcelColumnToNumber {

	/**
	 * 'A'=65, use index 0-25 to represent A-Z, so we need to use n-1
	 */
	public String convertToTitle(int n) {

		String s = "";
		while (n > 0) {
			n = n - 1;

			int b = n % 26;
			s = (char) ('A' + b) + s;

			n = n / 26;
		}

		return s;
	}

	public int titleToNumber(String s) {
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int a = c - 'A' + 1;
			res += a * Math.pow(26, s.length() - 1 - i);

		}
		return res;
	}

	public static void main(String[] args) {
		// A=65
		int a = 'A';
		char aa = 'A' + 1;
		System.out.println(a);
		System.out.println(aa);

		ExcelColumnToNumber e = new ExcelColumnToNumber();
		System.out.println(e.convertToTitle(1378));

	}

}