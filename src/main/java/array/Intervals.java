package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Intervals {

	/**
	 * sort intervals by start
	 * two pointers, previous, current. If curr.start <= pre.end, merge these two. Else, add pre into result list.
	 * @param args
	 */
	private class Interval {
		      int start;
		      int end;
		      Interval() { start = 0; end = 0; }
		      Interval(int s, int e) { start = s; end = e; }
		  }
	
	public List<Interval> merge(List<Interval> intervals) {
       
		if(intervals == null || intervals.size()==0){
        	return intervals;
        }
		//sort intervals by start
		Collections.sort(intervals, new Comparator<Interval>(){
			public int compare(Interval i1, Interval i2) {
				return i1.start - i2.start;
			}
		});
		
		List<Interval> result = new ArrayList<Interval>();
		Interval pre = intervals.get(0);
		for(int i=1; i<intervals.size(); i++){
			Interval curr = intervals.get(i);
			if(curr.start <= pre.end){
				//merge
				Interval merged = new Interval(pre.start, Math.max(pre.end, curr.end));
				pre = merged;
			}else{
				// add pre into result list
				result.add(pre);
				pre = curr;
			}
			
		}
		result.add(pre);
		
		return result;
		
    }

	/**
	 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
	 * if [1,2] curr.end < newInterval.start
	 * else if [12,16] curr.start > newInterval.end
	 * else newInterval new with current
	 */
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new ArrayList<Interval>();
		
		for(Interval curr : intervals){
			if(curr.end < newInterval.start){
				result.add(curr);
			}else if(curr.start > newInterval.end){
				result.add(newInterval);  //treat newInterval as previous
				newInterval = curr;
			}else{
				newInterval = new Interval(Math.min(curr.start, newInterval.start), Math.max(curr.end, newInterval.end));
			}
		}
		
		result.add(newInterval);
		return result;
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
