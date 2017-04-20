package array;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 1) {
            List<Integer> newlist = new ArrayList<>();
            newlist.add(nums[0]);
            res.add(newlist);
            return res;
        }
        
        for (int i=0; i<nums.length; i++) {
            //starts with i
            int[] arr = new int[nums.length - 1];
            for (int j=0; j<i; j++) {
                arr[j] = nums[j];
            }
            for (int j=i+1; j<nums.length; j++) {
                arr[j-1] = nums[j];
            }
            
            List<List<Integer>> others = permute(arr);
            for(List<Integer> list : others) {
                List<Integer> newlist = new ArrayList<>();
                newlist.add(nums[i]);
                newlist.addAll(list);
                res.add(newlist);
            }
            
        }
        
        return res;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Permutation obj = new Permutation();
		int[] arr = {2,3};
		System.out.println(obj.permute(arr));
		//System.out.println(obj.factorial(0));
		//System.out.println(obj.factorial(1));
		//System.out.println(obj.factorial(2));
		//System.out.println(obj.factorial(3));
		//System.out.println(obj.factorial(4));
		//System.out.println(0%2);
		//System.out.println(obj.getPermutation(3,4));
	}

}
