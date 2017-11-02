package math;

import java.util.HashSet;
import java.util.Set;

public class LineReflection {

	/**
	 * If there is a line then it should be at XLine = (minX + maxX) / 2.
	 */
	public boolean isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        Set<String> set = new HashSet<>();
        for (int[] point : points) {
            max = Math.max(point[0], max);
            min = Math.min(point[0], min);
            set.add(toString(point));
        }
        
        long sum = (long)max + (long)min;
        for (int[] point : points) {
            int reflectX = (int) (sum - point[0]);
            int[] reflect = {reflectX, point[1]};
            if (!set.contains(toString(reflect))) {
                return false;
            }
        }
        
        return true;
    }
    
    private String toString(int[] point) {
        return point[0] + "," + point[1];
    }
  
	public static void main(String[] args) {
		long a1 = (long)2147483600 + (long)2147483600;
		int x1 =(int) (a1 - 2147483600);
		System.out.println(a1);
		System.out.println(x1);
	}

}
