package array;

public class Number {

	/**
	 * @param args
	 */
	public int[] plusOne(int[] digits) {
        StringBuilder sb = new StringBuilder();
        int carry = 1;
        for(int i = digits.length-1; i>=0; i--){
            int sum = digits[i]+carry;
            if(sum==10){
                carry = 1;
                sum = 0;
            }else{
                carry = 0;
            }
            sb.append(sum);
        }
        
        if(carry==1) sb.append(1);
        
        String result = sb.reverse().toString(); //"1000"
        int size = result.length();
        int[] res = new int[size];
        for(int i=0; i<size; i++){
            res[i] = result.charAt(i) - '0';
        }
        
        return res;
        
        
    }
	
	public static int reverse(int x) {
		//be careful with the integer overflow
        boolean negative = false;
        if(x < 0){
            x = x * (-1);
            negative = true;
        }
        long res=0;
        while(x > 0){
            int remainder = x % 10;
            x = x / 10;
            res = res * 10 + remainder;
            if(res > Integer.MAX_VALUE)
                return 0;
        }
        
        if(negative){
        	res = res*(-1);
        	if(res < Integer.MIN_VALUE) return 0;
        }
      
        return (int) res;
        
        
        
    }
	
	
	 public int atoi(String str) {
	        
		 	if(str == null) return 0;
	        str = str.trim();
	        if(str.length() == 0) return 0;
	        
	        boolean isNeg = false;
	        if(str.startsWith("+")){
	        	str = str.substring(1);
	        }else if(str.startsWith("-")){
	        	isNeg = true;
	        	str = str.substring(1);
	        }
	        
	        long res = 0;
	        boolean afterDigit = false;
	        for(int i=0; i<str.length();i++){
	        	int num = str.charAt(i) -'0';
	        	if(num>=10 || num<0) {
	        		if(!afterDigit){ //+-0012 return 0
	        			return 0;
	        		}else{
	        			break;  //+012a48, return 12
	        		}
	        	}else{
	        	    afterDigit = true;
	        	    res = res*10 + num;
	        	    
	        	    if(res > Integer.MAX_VALUE) break;
	        	}
	        	
	        }
	        
	        if(isNeg){
	        	 res = res * (-1);
	        	 if(res < Integer.MIN_VALUE) return Integer.MIN_VALUE;
	        }else{
	             if(res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
	        }
	        
	        	        
	        return (int) res;
	        
 
	 }
	
	 /**
	  * calculate how many zeros
	  * compare left and right, using / and %
	  */
	 public static boolean isPalindrome(int x) {
		 if(x > Integer.MAX_VALUE || x < 0) return false;
         //initialize how many zeros, if x = 345543, div = 100000
         int div = 1;
         while(x/div >= 10){
             div = div*10;
         }
         
         while(x != 0){
             int left = x/div;
             int right = x % 10;
             if(left != right) return false;
             
             x = (x % div) / 10;
             div= div/100;
         }
         
         return true;
	        
	    }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		if(9646324351L > Integer.MAX_VALUE){
			System.out.println("bigger");
		}
		System.out.println(reverse(1534236469));
		
		isPalindrome(345543);
		int x = 345543;
		int div = 1;
        while(x/div >0){
            div = div*10;
        }
        System.out.println(div);
	}

}
