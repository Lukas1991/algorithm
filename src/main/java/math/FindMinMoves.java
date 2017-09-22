package math;

public class FindMinMoves {

	/**
	 * 517. Super Washing Machines
	 */
	public int findMinMoves(int[] machines) {        
        int n = machines.length;
        if (n == 0) return 0;
        
        int total = 0;
        for (int m : machines) {
            total += m;
        }
        
        if (total % n != 0) {
            return -1;
        }
        
        int avg = total / n;
        
        //for the case [0, 0, 0, 16], 1 machine has much larger number than others
        //do not need to abs(diff), because the smallest number is 0, cannot be a negative number -16
        int maxDiff = 0;
        for (int i = 0; i<n; i++) {
            int diff = machines[i] - avg;
            maxDiff = Math.max(maxDiff, diff);
        }
        
        //for the case [8,8,0,0], two large machines are neighbors
        //pass diff to the next machine is adding load to the next, then next machine's diff may be the largest
        int peak = 0; 
        for (int i = 0; i<n; i++) {
            int diff = machines[i] - avg;            
            peak = Math.max(peak, Math.abs(diff));
            if (i+1 < n) {
                machines[i+1] += diff;
            }
        }
        
        return Math.max(peak, maxDiff);
    }
	
	public static void main(String[] args) {
		FindMinMoves obj = new FindMinMoves();
		int max = obj.findMinMoves(new int[]{1, 0, 5});
	}

}
