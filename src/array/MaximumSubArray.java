package array;

public class MaximumSubArray {

	/**
	 * 
	 * dp O(n)
	 */
	public int maxSubArrayDP(int[] A) {
		int[] sum = new int[A.length];
		int max = A[0];
		sum[0] = A[0]; //initial value
		
		//sum[i] track the max sum of A[0 - i], ends at i!!!
		for(int i=1; i<A.length; i++){
			sum[i] = Math.max(sum[i-1]+A[i], A[i]); //if sum[i-1] < 0, ignore sum[i-1]
			System.out.println("A[i] is: "+A[i]+" sum[i] is: "+sum[i]);
			max = Math.max(max, sum[i]);  //change global max value
		}
		
		return max;
		
	}
	
	public int maxSubArrayBetter(int[] A) {
		int newsum = A[0];//instead of using an arr, track the max sum of A[0 - i], ends at i!!!
		int max = A[0];
				
		for(int i=1; i<A.length; i++){
			newsum = Math.max(newsum + A[i], A[i]); //if sum[i-1] < 0, ignore sum[i-1]
			
			max = Math.max(max, newsum);  //change global max value
		}
		
		return max;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
		MaximumSubArray ms = new MaximumSubArray();
		ms.maxSubArrayDP(arr);
	}

}
