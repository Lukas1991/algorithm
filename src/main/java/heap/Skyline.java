package heap;

import java.util.*;

public class Skyline {

    public List<int[]> getSkyline(int[][] buildings) {
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

    /**
     * Building Outline
     * use TreeMap as maxHeap to avoid time exceed
     */
    public List<int[]> getSkylineTreeMap(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for (int[] b : buildings) {
            height.add(new int[]{b[0], -b[2]});  //left point
            height.add(new int[]{b[1], b[2]});     //right point
        }

        //sort by x ASC, H ASC
        Collections.sort(height, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        //max heap to store possible heights
        //key is height, value is count of duplicated height
        TreeMap<Integer, Integer> maxHeapMap = new TreeMap<>(Comparator.reverseOrder());

        //initial value
        maxHeapMap.put(0, 0);

        int prev = 0;
        for (int[] h : height) {
            if (h[1] < 0) {
                // a start point, add height
                int key = -h[1];
                maxHeapMap.put(key, maxHeapMap.getOrDefault(key, 0) + 1);

            } else {
                // a end point, remove height
                int key = h[1];
                removeFromHeap(maxHeapMap, key);
            }

            // compare current max height with previous max height,
            //if there's height change, add to result
            int cur = maxHeapMap.firstKey();
            if (prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return result;
    }

    private void removeFromHeap(TreeMap<Integer, Integer> map, int key) {
        int count = map.get(key);
        count--;
        if (count == 0) {
            map.remove(key);
        } else {
            map.put(key, count);
        }
    }

    private List<List<Integer>> convert(List<int[]> heights) {
        List<List<Integer>> res = new ArrayList<>();
        if (!heights.isEmpty()) {
            int[] pre = heights.get(0);

            for (int i = 1; i < heights.size(); i++) {
                int[] cur = heights.get(i);

                if (pre[1] != 0) {
                    List<Integer> node = new ArrayList<>();
                    node.add(pre[0]);
                    node.add(cur[0]);
                    node.add(pre[1]);
                    res.add(node);
                }

                pre = cur;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Skyline obj = new Skyline();
        int[][] buildings = {{1, 3, 3}, {2, 4, 4}, {5, 6, 1}};
        //{{2, 9,10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        //{{4,10,10},{5,10,9},{6,10,8},{7,10,7},{8,10,6},{9,10,5}};
        List<int[]> result = obj.getSkyline(buildings);
        for (int[] arr : result) {
            System.err.println(Arrays.toString(arr));
        }

        List<List<Integer>> res = obj.convert(result);
        for (List<Integer> list : res) {
            System.err.println(list.toString());
        }
    }

}
