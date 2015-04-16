package Interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * onsite: k way merge sorted stream. each time get smallest of K sorted stream. 
 * find maximum consecutive character length, "aabacccee" longest is ccc, return 3.
 */
/**
 * This file contains one Interval class, and one solution IPREO class.
 * IPREO class contains a merge() method, which is the solution, and 3 test cases.
 *
 */

class Interval {
	      int start;
	      int end;
	      Interval() { start = 0; end = 0; }
	      Interval(int s, int e) { start = s; end = e; }
	      public String toString(){
	    	  return "{ " + start + "," +end + " }";
	      }
	  }

public class IPREO {

	/**
	 * The merge method will sort the list of Intervals by their start number, then iterate through the list, comparing the current interval with its previous.
	 * If current interval start number <= previous's start, merge current interval with the previous. continue to the next interval.
	 * Else, means there's no overlapping for current internal and its previous, add previous into result list.
	 * After the iteration, need to save the last previous interval into result list.
	 * @param intervals
	 * @return
	 */
	 public List<Interval> merge(List<Interval> intervals) {
	        	       
			if(intervals == null || intervals.size()==0){
	        	return intervals;
	        }
			
			//sort the list of Intervals by their start number
			Collections.sort(intervals, new Comparator<Interval>(){
				public int compare(Interval i1, Interval i2) {
					return i1.start - i2.start;
				}
			});
			
			List<Interval> result = new ArrayList<Interval>();
			Interval previous = intervals.get(0);
			for(int i=1; i<intervals.size(); i++){
				Interval current = intervals.get(i);
				if(current.start <= previous.end){
					//merge current interval with previous
					Interval merged = new Interval(previous.start, Math.max(previous.end, current.end));
					previous = merged;
				}else{
					// add previous into result list
					result.add(previous);
					previous = current;
				}
				
			}
			
			result.add(previous);
			
			return result;
			
	    
	    }
	
	 /**
	 * Test case 1
	 * given set of intervals: { 1,3 }, { 2,4 }, { 5,7 }, { 6,8 }
	 * set of merged intervals should be: { 1,4 }, { 5,8 }
	 */
	 public static void test1(){
		    List<Interval> intervals = new ArrayList<Interval>();
			
			Interval in1 = new Interval(1,3);
			Interval in2 = new Interval(2,4);
			Interval in3 = new Interval(5,7);
			Interval in4 = new Interval(6,8);
			
			intervals.add(in1);
			intervals.add(in2);
			intervals.add(in3);
			intervals.add(in4);
			
			System.out.println("-------Given intervals-----");
			for(Interval in : intervals){
				System.out.println(in.toString());
			}
			
			System.out.println("-------After merge-----");
			IPREO obj = new IPREO();
			List<Interval> merged = obj.merge(intervals);
			
			for(Interval in : merged){
				System.out.println(in.toString());
			}
	 }
	 
	 /**
		 * Test case 2 - no overlapping
		 * given set of intervals: { 1,2 }, { 5,6 }, { 8,9 }
		 * set of merged intervals should be the same
		 */
	 public static void test2(){
		    List<Interval> intervals = new ArrayList<Interval>();
			
			Interval in1 = new Interval(1,2);
			Interval in2 = new Interval(5,6);
			Interval in3 = new Interval(8,9);
			
			intervals.add(in1);
			intervals.add(in2);
			intervals.add(in3);
			
			System.out.println("-------Given intervals-----");
			for(Interval in : intervals){
				System.out.println(in.toString());
			}
			
			System.out.println("-------After merge-----");
			IPREO obj = new IPREO();
			List<Interval> merged = obj.merge(intervals);
			
			for(Interval in : merged){
				System.out.println(in.toString());
			}
	 }
	 
	 /**
		 * Test case 3 - more than two consecutive intervals overlaps
		 * given set of intervals: { 1,6 }, { 2,4 }, { 5,9 }, { 8,12 }
		 * set of merged intervals should be the same
		 */
	 public static void test3(){
		    List<Interval> intervals = new ArrayList<Interval>();
			
			Interval in1 = new Interval(1,6);
			Interval in2 = new Interval(2,4);
			Interval in3 = new Interval(5,9);
			Interval in4 = new Interval(8,12);
			
			intervals.add(in1);
			intervals.add(in2);
			intervals.add(in3);
			intervals.add(in4);
			
			System.out.println("-------Given intervals-----");
			for(Interval in : intervals){
				System.out.println(in.toString());
			}
			
			System.out.println("-------After merge-----");
			IPREO obj = new IPREO();
			List<Interval> merged = obj.merge(intervals);
			
			for(Interval in : merged){
				System.out.println(in.toString());
			}
	 }
	 
	 	 
	 
	public static void main(String[] args) {
		
		//test1();
		//test2();
		 test3();
	}

}
