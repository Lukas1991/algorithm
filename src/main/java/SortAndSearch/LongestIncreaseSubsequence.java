package SortAndSearch;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreaseSubsequence {

	/** non-contiguous, speed is O(n^2)
	 * @param args
	 */
	public static int increasingSubsequence(int[] seq){
		int[] L=new int[seq.length];
		L[0]=1;
		for(int i=1;i<L.length;i++){
			int maxn=0;
			for(int j=0;j<i;j++){
				if(seq[j]<seq[i] && L[j]>maxn)  maxn=L[j];
			}
			L[i]=maxn+1;
		}
		 int maxi=0;
		 for(int i=0;i<L.length;i++){
			 if(L[i]>maxi) maxi=L[i];
		 }
		return maxi;
	}
	
	public static ArrayList<Integer> increasing(int[] a){
		ArrayList<Integer>[] solutions=new ArrayList[a.length];
		increasing(a,solutions,0);
		
		System.out.println(Arrays.toString(solutions));
		//find max_size's index
		int i=0; int max_size=0;
		for(int j=0;j<solutions.length;j++){
			if(solutions[j].size()>max_size){
				max_size=solutions[j].size();i=j;
			}
		}
		System.out.println("max length is:"+max_size);
		ArrayList<Integer> result=solutions[i];
		return result;
	}
	
	public static void increasing(int[] a, ArrayList<Integer>[] solutions, int index){
		System.out.println("index is: "+index);
		if(index>=a.length) return;
		ArrayList<Integer> best_seq=null;
		for(int i=0;i<index;i++){
			if(a[i]<a[index]){
				best_seq=seqMaxLength(best_seq,solutions[i]);
				System.out.println(best_seq);
			}
		}
		System.out.println("best seq: "+best_seq);
		ArrayList<Integer> new_seq=new ArrayList<>();
		if(best_seq!=null) new_seq.addAll(best_seq);
		new_seq.add(a[index]);
		solutions[index]=new_seq;
		System.out.println("new_seq: "+new_seq);
		
		increasing(a,solutions,index+1);
	}
	
	
	
	public static ArrayList<Integer> seqMaxLength(ArrayList<Integer>s1,ArrayList<Integer>s2){
		if(s1==null) return s2;
		if(s2==null) return s1;
		return s1.size()>s2.size()? s1:s2;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a={0,7,3,5,9,4,8};
		//int result=increasingSubsequence(a);
		//System.out.println(result);
		
		ArrayList<Integer> result=increasing(a);
		System.out.println("result: "+result);
		
		
	}

}
