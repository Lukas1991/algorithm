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
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
