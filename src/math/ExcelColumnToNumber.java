package math;

public class ExcelColumnToNumber {

	public String convertToTitle(int n) {
		//'A'=65, use index 1-26 to represent A-Z, when the remainder is 0, that is Z.
		//(Q-remainder)/26 is always an integer
		
		if(n<=0) return "Not Valid";
		
		int Q=n;
		String result="";
		
		while(Q>0){
			int R=Q%26;
			if(R==0){
				result = 'Z' + result;
				Q=Q-26;
			}else{
				char rr=(char) ('A'- 1 + R);
				result = rr + result;
				Q = Q - R;
			}			
			Q= Q/26;			
		}
		
        return result;
    }
	
	
	
	public String convertToTitle2(int n) {
		//'A'=65, use index 0-25 to represent A-Z, so we need to use Q-1
		
		if(n<=0) return "Not Valid";
		
		int Q=n-1;
		String result="";
		
		while(Q>=0){
			int R=Q%26;
			char rr=(char) ('A'+ R);
			result = rr + result;
			Q= Q/26 -1;			
		}
		
        return result;
    }
	
	
	
	
	 public int titleToNumber(String s) {
         if(s==null) return 0;
	        
	        String str=s.trim().toUpperCase();
	        if(str=="") return 0;
	        
	        int length=str.length();
	        int sum=0;
	        
	        for(int i=0;i<length;i++){
	            double power = (double) length-1.0-(double)i;
	            sum += (str.charAt(i)-64) * Math.pow((double)26,power);	            
	        }
	        return sum;
	        
        
        
    }
    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//A=65
		int a='A';
		char aa='A'+1;
		System.out.println(a);
		System.out.println(aa);
		
		ExcelColumnToNumber e=new ExcelColumnToNumber();
		System.out.println(e.convertToTitle(1378));
		
	}

}
