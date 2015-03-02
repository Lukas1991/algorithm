package string;

public class ValidPalindrome {

	/**
	 * @param args
	 */
	public boolean isAlphaNumeric(char c){
        if((c >= 'a' && c<='z') ||(c >= 'A' && c<='Z') ||(c >= '0' && c<='9') ){
            return true;
        }else
            return false;
            
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ValidPalindrome obj = new ValidPalindrome();
		System.out.println(obj.isAlphaNumeric('a'));
		System.out.println(obj.isAlphaNumeric('A'));
		System.out.println('a'-'A');
		//Stack<Character> st = new Stack<Character>();
		String str="hey";
		System.out.println();
		//str.indexOf(str)
		//str.equals(anObject)
		
		
	}

}
