package string;

public class AddBinary {

	 public String addBinary(String a, String b) {
	        char[] ac = a.toCharArray();
	        char[] bc = b.toCharArray();
	        
	        int carry = 0;
	        int sum = 0;
	        int endA = ac.length-1;
	        int endB = bc.length-1;
	        StringBuilder sb = new StringBuilder();

	        while (endA >= 0 || endB >= 0) {
	            int i = endA >= 0 ? ac[endA] - '0' : 0;
	            int j = endB >= 0 ? bc[endB] - '0' : 0;
	            sb.append((carry + i + j) % 2);
	            carry = (carry + i + j) / 2;
	            endA--;
	            endB--;
	        }
	        
	        if(carry == 1) sb.append('1');
	        
	        return sb.reverse().toString();
	    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}