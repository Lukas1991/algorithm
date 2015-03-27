package dp;

import java.util.*;
import java.util.Set;

public class WordBreak {

	/**
	 * Define an array dp[](size is s.length+1) such that dp[i]==true indicates 0-(i-1) can be segmented using dictionary
	 * Initial state dp[0] == true, return dp[s.length()]
	 * dp[k] refers to s.substring(0,k)
	 * O(n^2)
	 * @param args
	 */
	public boolean wordBreak(String s, Set<String> dict) {
		
		boolean dp[] = new boolean[s.length()+1];
		dp[0] = true; //set initial state to be true
		
		for(int i=1; i<=s.length(); i++){
			for(int k=0; k<i; k++){
				//if dp[k] refers to s.substring(0,k)
				if(dp[k] && dict.contains(s.substring(k, i))){
					dp[i] = true;
					break;
				}
				
			}
		}
		
		return dp[s.length()];
		
	}
	/**
	 * use a dp array, List<String>[], to track actual words. when dp[i] not null, indicates 0-(i-1) can be segmented,
	 * dp[i] contains the substrings in dict, which start from any previous segmented index, end to i-1
	 * which means: dp[k]!=null && dict.contains(s.substring(k, i))
	 * 
	 * After dp array is ready, dfs, root is the end word, "dog", find all paths from end to start
	 * Test dp[]: 
	 * index: 0  
	 * index: 3  cat, 
	 * index: 4  cats, 
	 * index: 7  sand, and, 
	 * index: 10  dog,
	 */

	public static List<String> wordBreak2(String s, Set<String> dict) {
	    //create an array of ArrayList<String>
	    List<String>[] dp = new ArrayList[s.length()+1];
	    dp[0] = new ArrayList<String>(); //set initial state to be not null
	 
	    //if dp[k]==null, s.substring(0,k) cannot be segmented
	    for(int i=1; i<=s.length(); i++){
	    	for(int k=0; k<i; k++){
	    		//if s.substring(0,k) can be segmented, and s.substring(k, i) in dict, add s.substring(k, i) into list dp[i]
	    		if(dp[k]!=null && dict.contains(s.substring(k, i))){
	    			if(dp[i]==null){
	    				dp[i] = new ArrayList<String>();
	    			}
	    			dp[i].add(s.substring(k, i));
	    		}
	    	}
	    }
	    
	/*    
	    System.out.println("Test dp[]: ");
	    for(int i=0; i<dp.length; i++){
	    	List<String> list = dp[i];
	    	if(list != null){
	    		System.out.print("index: "+i+"  ");
	    		for(String str:list){
	    			System.out.print(str+", ");
	    		}
	    		System.out.print("\n");
	    	}
	    }
	  */  
	    
	    
	    List<String> result = new LinkedList<String>();
	    if(dp[s.length()] == null)  //cannot be segmented
	        return result; 
	 
	    ArrayList<String> temp = new ArrayList<String>();
	    //dfs, root is the end word, "dog", find all paths from end to start
	    dfs(dp, s.length(), result, temp);
	 
	    return result;
	}
	 
	public static void dfs(List<String> dp[],int end,List<String> result, ArrayList<String> tmp){
		//return condition
		if(end<=0){
			//tmp will have dog, and, cats
			String res="";
			for(int i=tmp.size()-1; i>=0; i--){
				res = res+tmp.get(i);
				if(i>0) res = res+" ";
			}
			result.add(res);
		}
				
		
		for(String str : dp[end]){
			tmp.add(str);
			dfs(dp, end-str.length(), result, tmp);
			//remove by index, the last one just added
			tmp.remove(tmp.size()-1);
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> dict = new HashSet<String>();
		dict.add("cat");
		dict.add("cats");
		dict.add("and");
		dict.add("sand");
		dict.add("dog");
		
		wordBreak2("catsanddog", dict);
		Set<String> dict1 = new HashSet<String>();
		dict1.add("aaaa");
		dict1.add("aa");
		//wordBreak2("aaaaaa", dict1);
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.add("dog");tmp.add("cats");tmp.add("and");tmp.add("cats");
		tmp.remove("cats");
		System.out.println(tmp.size());
		for(String s: tmp){
			System.out.println(s);
		}
	}

}
