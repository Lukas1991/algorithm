package array;

/**
 *Given a string, find the length of the longest substring without repeating characters. 
 *For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
 *For "bbbbb" the longest substring is "b", with the length of 1.
 *
 */

public class LengthOfLongestSubstring {

	/**
	 * when find a character flag is true, say 'a', find this same character index in the substring.
	 * mark the character before 'a' flag all 0
	 * move the j index to the character next to 'a', so the new substring is from the new index j
	 */
	
	public static int lengthOfLongestSubstringOld(String s) {
		boolean[] flag = new boolean[256];
	 
		int result = 0;
		int j = 0;
		char[] arr = s.toCharArray();
	 
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if (flag[c]) {
				//from index i to j is the substring
				result = Math.max(result, i - j);
				//find the same character in this substring
				for (int k = j; k < i; k++) {
					if (arr[k] == c) {
						j = k + 1;
						break;
					}
					flag[arr[k]] = false;
				}	
			} else {
				flag[c] = true;
			}
		}
	 
		result=Math.max(arr.length-j,result);
	 
		return result;
	}

	public static int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0) return 0;
		int[] arr = new int[256];
		int max = 1;

		int i=0, j=1;
		arr[s.charAt(0)] = 1;
		while(j<s.length()) {
			if (arr[s.charAt(j)] == 0) {
				arr[s.charAt(j)] = 1;
				j++;
			} else {
				int length = j-i;
				max = Math.max(max, length);
				//move i
				while (i<=j && arr[s.charAt(j)] > 0) {
					arr[s.charAt(i)] = arr[s.charAt(i)] - 1;
					i++;
				}
				arr[s.charAt(j)] = 1;
				j++;
			}

		}

		int length = j-i;
		max = Math.max(max, length);
		return max;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String s="abcabcbb";
		String s="abcaxyzwabcbb";
		int result=lengthOfLongestSubstring(s);
		System.out.println(result);
		
		//"abc"-  j=0
		//"abca" -j=1, new substring is "bca"
		//"abcaxyzw"  j=1 new substring is "bcaxyzw"
		//"abcaxyzwa"  j=4 new substring is "xyzwa"
		//"abcaxyzwabc"  j=4 new substring is "xyzwabc"
		
	}

}