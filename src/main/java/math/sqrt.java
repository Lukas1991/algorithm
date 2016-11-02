package math;

public class sqrt {

	
	public int sqrt(int x) {
        	    
        int i=0,j=x/2+1;
        while(i<=j){      	
            double mid=(i+j)/2;
            double r=mid*mid;
            //System.out.println("i is: "+i+" j is: "+j+" mid is: "+mid+" r is: "+r);
            if(r==x) return (int) mid;
            if(r<x) i=(int) (mid+1);
            if(r>x) j=(int) mid-1;
        }
        return j;
	}
	
	public int sqrtSLOW(int x) {
		
		for(int i=0;i<=x/3+1;i++){
            int r=i*i;
            if(r==x) return i;
            if(r>x){
                return -1;
            }
        }
        return -1;

	}
	
	 public int sqrtNewton(int x) {
	       if (x == 0) return 0;
	       final int SCALE = 100;

	       double x1;
	       double x0 = x / 2.0;

	       int i = 0;
	       while (++i < SCALE) {
	           x1 = x0 - (x0 * x0 - x) / (2 * x0);
	           if (x0 == x1) break;
	           x0 = x1;
	       }
	       return (int) x0;
	   }
	 
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sqrt s = new sqrt();
		int result = s.sqrt(10);
		//int result = s.sqrt(100000000);
		System.out.println(result);
		//double i=12499999;
		//System.out.println(i*i);
	}

}