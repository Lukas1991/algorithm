package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {
	
	/**
	 * Permutations 1, Given a collection of distinct numbers, return all possible permutations.
	 */
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)   return res;
        List<Integer> tmp = new ArrayList<>();
        
        helper(nums, tmp, res);        
        return res;
    }
    
    private void helper(int[] nums, List<Integer> tmp, List<List<Integer>> res) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            if (!tmp.contains(a)) {
                tmp.add(a);
                
                helper(nums, tmp, res);
                
                tmp.remove(tmp.size() - 1);
            }
        }
        return;
    }
    
    /**
     * Permutations 2, Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     * 
     * Sort the nums, keep a boolean[] used to mark whether nums[i] has been added to the list.
     * 
     * e.g. nums=[1,2,2,3] and tmp list = [1,2]
     * after we get results [1,2,2,3] and [1,2,3,2], which both start with [1,2]
     * we remove 2 from tmp list, tmp list = [1]
     * 
     * Then when we scan to the second 2, we should skip it to avoid another tmp = [1,2], now nums[i] == nums[i-1] && !used[i-1] 
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)   return res;
        
        Arrays.sort(nums);
        List<Integer> tmp = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
            
        helper2(nums, tmp, res, used);        
        return res;
    }
    
    private void helper2(int[] nums, List<Integer> tmp, List<List<Integer>> res, boolean[] used) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i>0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            
            used[i] = true;
            tmp.add(nums[i]);
            
            helper2(nums, tmp, res, used);
            used[i] = false;
            tmp.remove(tmp.size() - 1);
        }       
    }

    //Another accepted helper3, check nums[i+1] == nums[i], move i to the next different number
    private void helper3(int[] nums, List<Integer> tmp, List<List<Integer>> res, boolean[] used) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i])    continue;

            tmp.add(nums[i]);
            used[i] = true;
            helper3(nums, tmp, res, used);
            tmp.remove(tmp.size() - 1);
            used[i] = false;

            while (i+1 < nums.length && nums[i+1] == nums[i]) {
                i++;
            }
        }
    }
    
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
