package Interview.amzn.oa;

import java.util.Arrays;

/**
 * https://yeqiuquan.blogspot.com/2017/03/lintcode-604-window-sum.html
 * 
 * @author dacai
 *
 */
public class WindowSum {

	public int[] winSum(int[] nums, int k) {

		int[] ret = new int[nums.length - k + 1];

		for (int i = 0; i < k; i++) {

			ret[0] += nums[i];
		}

		for (int i = 1; i < nums.length - k + 1; i++) {
			ret[i] = ret[i - 1] - nums[i - 1] + nums[i - 1 + k];
		}

		Arrays.asList(ret);
		return ret;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "i";
		String b = "love";
		System.out.println(a.compareTo(b));
		
	}

}