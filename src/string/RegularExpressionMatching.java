package string;

public class RegularExpressionMatching {

	/**
	 * @param args
	 */
		public boolean isMatch(String s, String p) {
	        return isMatch(s, 0, p, 0);
	    }
	    
	    public boolean isMatch(String s, int i, String p, int j) {
	        if (j == p.length()) return i == s.length();
	        
	        // arrive the end of pattern, and it must not be *, cuz line 16 already catched it in last call
	        if (j == p.length() - 1) {
	            return i == s.length() - 1 && charMatch(s, i, p, j);
	        }
	        
	        // if j+1 is not *
	        // then both of current chars should be matched, and continue to check both of next char
	        if (p.charAt(j+1) != '*') {
	            return charMatch(s, i, p, j) && isMatch(s, i+1, p, j+1);
	        }
	        
	        // j+1 is *. try check s current chars match with the p char behind * , which * refers to 0 in s
	        if (isMatch(s, i, p, j+2)) return true;
	        
	        //j+1 is *, * refers to one or more in s
	        while (charMatch(s, i, p, j)) {
	            if (isMatch(s, i+1, p, j+2)) {
	                return true;
	            }
	            i++;
	        }
	        
	        return false;
	    }
	    
	    public boolean charMatch(String s, int i, String p, int j) {
	        if (i >= s.length() || j >= p.length()) return false;
	        
	        return s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';
	    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
