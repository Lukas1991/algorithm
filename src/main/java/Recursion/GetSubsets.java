package Recursion;

import java.util.ArrayList;

public class GetSubsets {
	//recursion
	public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index){
		ArrayList<ArrayList<Integer>> allsubsets;
		if(set.size()==index){
			allsubsets=new ArrayList<ArrayList<Integer>>();
			allsubsets.add(new ArrayList<Integer>());	//add a null subset		
		}else{
			allsubsets=getSubsets(set, index+1);
			System.out.println("allsubsets: "+allsubsets+" index is: "+index);
			int item=set.get(index);
			ArrayList<ArrayList<Integer>> moresubsets=new ArrayList<ArrayList<Integer>>();
			
			for(ArrayList<Integer> subset:allsubsets){
				ArrayList<Integer> newsubset=new ArrayList<Integer>();
				newsubset.addAll(subset);
				newsubset.add(item);
				moresubsets.add(newsubset);
			}
			
			System.out.println("moresubsets: "+moresubsets);
			allsubsets.addAll(moresubsets);
		}
		System.out.println("in index "+index+" return allsubsets: "+allsubsets);
		return allsubsets;
	}
	//Iteration
	public static ArrayList<ArrayList<Integer>> getSubsets2(ArrayList<Integer> set) {
		ArrayList<ArrayList<Integer>> allsubsets =new ArrayList<ArrayList<Integer>>();
		 int max = 1 << set.size();
		 for (int i = 0; i < max; i++) {
			 ArrayList<Integer> subset = new ArrayList<Integer>();
			 int k = i;
			 int index = 0;
			 while (k > 0) {
				 if ((k & 1) > 0) {
					 subset.add(set.get(index));
				 }
				 k >>= 1;
		 		index++;
			 }
			 System.out.println("i is: "+i+" subset: "+subset);
			 allsubsets.add(subset);
			 System.out.println("in i: "+i+" return allsubsets: "+allsubsets);
		 }
		
		 return allsubsets;
		 }
	
	
	public static void main(String[] args) {
		ArrayList<Integer> set=new ArrayList<Integer>();
		set.add(1);set.add(2);set.add(3);
		System.out.println(set);
		//System.out.println(getSubsets(set,0));
		System.out.println(getSubsets2(set));

	}

}
