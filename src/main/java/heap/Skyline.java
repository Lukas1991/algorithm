package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Skyline {

	public static List<int[]> getSkyline(int[][] buildings) {
	    List<int[]> result = new ArrayList<>();
	    List<int[]> height = new ArrayList<>();
	    for(int[] b:buildings) {
	        height.add(new int[]{b[0], -b[2]});  //left point
	        height.add(new int[]{b[1], b[2]});	 //right point
	    }
	   
	    //sort by x ASC, H ASC
	    Collections.sort(height, (a, b) -> {
	            if(a[0] != b[0]) 
	                return a[0] - b[0];
	            return a[1] - b[1];
	    });
	    
//	    for (int[] arr : height) {
//			System.err.println(Arrays.toString(arr));
//		}
	    
	    //max heap to store possible heights
	    Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
	    //initial value
	    pq.offer(0);
	    int prev = 0;
	    for(int[] h:height) {
	        if(h[1] < 0) {
	        	// a start point, add height
	            pq.offer(-h[1]);
	        } else {
	        	// a end point, remove height
	            pq.remove(h[1]);
	        }
	        
	        // compare current max height with previous max height, 
	        //if there's height change, add to result
	        int cur = pq.peek();
	        if(prev != cur) {
	            result.add(new int[]{h[0], cur});
	            prev = cur;
	        }
	    }
	    return result;
	}
	
	public static void main(String[] args) {
		int[][] buildings = {{2, 9,10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
			//{{4,10,10},{5,10,9},{6,10,8},{7,10,7},{8,10,6},{9,10,5}};
		List<int[]> result = getSkyline(buildings);
		for (int[] arr : result) {
			System.err.println(Arrays.toString(arr));
		}
	}

}
