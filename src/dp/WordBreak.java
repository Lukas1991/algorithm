package dp;

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
		
		for(int i=0; i<=s.length(); i++){
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
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
