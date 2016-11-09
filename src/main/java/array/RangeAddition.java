package array;

/**
 * Assume you have an array of length n initialized with all 0's and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.
 *
 *Given:

    length = 5,
    updates = [
        [1,  3,  2],
        [2,  4,  3],
        [0,  2, -2]
    ]

Output:

    [-2, 0, 3, 5, 3]
 */
public class RangeAddition {
	
	//Better solution: only change res[startIndex], and res[endIndex+1]
	//res[index] represents for the diff(increment or decrement) compare to previous element. 
	public int[] rangeAdditionBetter(int n, int[][] updates) {
		if (n<=0) return null;
		
		int[] res = new int[n];
		
		for(int[] row: updates) {
			int start = row[0];
			int end = row[1];
			
			res[start] += row[2];
			if (end + 1 < n) {
				res[end+1] -= row[2];
			}
		}
		
		int sum = res[0];
		for(int j = 1; j<n; j++) {
			sum += res[j];
			res[j] = sum;
		}
		
		return res;
	}
	
	public int[] rangeAddition(int n, int[][] updates) {
		if (n<=0) return null;
		
		int[] res = new int[n];
		for (int i=0; i<updates.length; i++) {
			int[] row = updates[i];
			for(int j = row[0]; j<= row[1]; j++) {
				res[j] += row[2];
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		int[][] updates = {
				{1, 3, 2},
				{2, 4, 3},
				{0, 2, -2}
		};
		
		RangeAddition ra = new RangeAddition();
		// int[] res = ra.rangeAddition(5, updates);
		int[] res = ra.rangeAdditionBetter(5, updates);
		
		for(int a: res) {
			System.err.println(a);
		}
		
	}	
		

}
