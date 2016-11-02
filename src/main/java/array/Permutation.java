package array;

import java.util.ArrayList;

public class Permutation {

	/**
	 * @param args
	 */
	
	public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=1; i<=n; i++){
            list.add(i);
        }
        getPermutation(n, k, sb, list);
        return sb.toString();    
    }
    
     public void getPermutation(int n, int k,  StringBuilder sb, ArrayList<Integer> list ) {
         if(n<=0){return;}

         int q = (k-1)/factorial(n-1);
         int r = (k-1)%factorial(n-1);
         
         int num=list.get(q);
         sb.append(num);
         list.remove(q);
        
         getPermutation( n-1,  r+1,  sb, list);
     }
    
	
	public int factorial(int n){
		if(n==0) return 1;
        int prod=1;
        for(int i=1;i<=n; i++){
            prod = prod * i;
        }
        return prod;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Permutation obj = new Permutation();
		//System.out.println(obj.factorial(0));
		//System.out.println(obj.factorial(1));
		//System.out.println(obj.factorial(2));
		//System.out.println(obj.factorial(3));
		//System.out.println(obj.factorial(4));
		System.out.println(0%2);
		System.out.println(obj.getPermutation(3,4));
	}

}
