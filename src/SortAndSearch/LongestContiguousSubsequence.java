package SortAndSearch;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestContiguousSubsequence {

	/**contiguous
	 * @param args
	 */
	public static ArrayList<Integer> increasing(int[] a){
		ArrayList<Integer>[] solutions=new ArrayList[a.length];
		increasing(a,solutions,0);
		
		System.out.println(Arrays.toString(solutions));
		//find max_size's index
		int i=0; int max_size=0;
		for(int j=0;j<solutions.length;j++){
			if(solutions[j].size()>max_size){
				max_size=solutions[j].size();
				i=j;
			}
		}
		System.out.println("max length is:"+max_size);
		ArrayList<Integer> result=solutions[i];
		return result;		
	}
	
	public static void increasing(int[] a,ArrayList<Integer>[] solutions,int index){
		System.out.println("index is: "+index);
		if(index>=a.length) return;

		ArrayList<Integer> new_seq=new ArrayList<>();
		if(index==0) new_seq.add(a[index]);
		else{
			ArrayList<Integer> previous_seq=solutions[index-1];
			//last element in previous_seq is a[index-1]
			if(a[index]>a[index-1]){
				new_seq.addAll(previous_seq);
				new_seq.add(a[index]);
			} 
			else{
				new_seq.add(a[index]);				
			}
			
		}
		
		System.out.println("new_seq: "+new_seq);
		solutions[index]=new_seq;
		
		increasing(a,solutions,index+1);
	}
	//only use one array.
	//do not save previous solutions
	public static void GetSeqWithArray(int[] a){
		int maxIndex=0,maxLast=0,maxSize=1;
		int thisIndex=0,thisLast=0,thisSize=1;
		for(int i=1;i<a.length;i++){
			if(a[i]>a[thisLast]){
				thisLast++;thisSize++;
				if(thisSize>maxSize){
					maxIndex=thisIndex;maxLast=thisLast;maxSize=thisSize;
				}
			}else{
				thisIndex=i;thisLast=i;thisSize=1;
			} 
		}
		System.out.println("maxSize: "+maxSize);
		for(int j=maxIndex;j<=maxLast;j++){
			System.out.print(a[j]+", ");
		}
		
	}
	
	public static void main(String[] args) {
		int[] a={0,7,3,5,9,4,8,10};
		ArrayList<Integer> result=increasing(a);
		System.out.println("result: "+result);
		//GetSeqWithArray(a);
	}

}
