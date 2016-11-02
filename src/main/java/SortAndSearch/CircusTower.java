package SortAndSearch;

import java.util.ArrayList;
import java.util.Collections;

class HtWt implements Comparable{
	private int Ht=0;
	private int Wt=0;
	public HtWt(int h,int w){
		Ht=h;Wt=w;
	}
	
	public String toString(){
		String s="("+Ht+","+Wt+")";
		return s;
	}
	
	public boolean isBefore(HtWt it){		
		if(this.Ht<it.Ht && this.Wt<it.Wt) return true;
		else return false;
	}

	@Override
	public int compareTo(Object o) {	
		HtWt second=(HtWt) o;
		if(this.Ht != second.Ht){
			return ((Integer)this.Ht).compareTo(second.Ht);
		}else{
			return ((Integer)this.Wt).compareTo(second.Wt);
		}				
	}
	
}

public class CircusTower {

	public static ArrayList<HtWt> items = new ArrayList<>();
	public static ArrayList<HtWt> lastFoundSeq=new ArrayList<>();
	public static ArrayList<HtWt> maxSeq=new ArrayList<>();
	
	public static ArrayList<HtWt> seqWithMaxLength (ArrayList<HtWt> seq1, ArrayList<HtWt> seq2){
		return seq1.size() > seq2.size() ? seq1 : seq2;
	}
	
	public static int fillNextSeq(int startFrom, ArrayList<HtWt> seq){
		ArrayList<Integer> Unfitlist=new ArrayList();
		int firstUnfitItem=startFrom;
		if(startFrom<items.size()){
			for(int i=startFrom;i<items.size();i++){
				HtWt item=items.get(i);
				if(i==startFrom || items.get(i-1).isBefore(item)){
					seq.add(item);
				}else{
					Unfitlist.add(i);
					firstUnfitItem=i;
				}
			}						
		}
		
		if(Unfitlist.size()>0){
			firstUnfitItem=Unfitlist.get(0);
			/*System.out.println("firstUnfitItem: "+firstUnfitItem);
			for(int i=startFrom;i<firstUnfitItem-1;i++){
				lastFoundSeq.add(items.get(i));
			}
			
			System.out.println("lastFoundSeq: "+lastFoundSeq);*/
			return firstUnfitItem;
		}else return startFrom;
		
		//return firstUnfitItem;	
	}
	
	public static void findMaxSeq(){
		Collections.sort(items);
		System.out.println(items);
		int currentUnfit=0;
		while(currentUnfit < items.size()){
			ArrayList<HtWt> nextSeq=new ArrayList<>();
			//ArrayList<HtWt> nextSeq=lastFoundSeq;
			int nextUnfit=fillNextSeq(currentUnfit,nextSeq);
			System.out.println("currentUnfit: "+currentUnfit+" nextUnfit: "+nextUnfit);
			System.out.println("nextSeq: "+nextSeq);
			//lastFoundSeq.addAll(nextSeq);
			//System.out.println("lastFoundSeq: "+lastFoundSeq);
			
			maxSeq=seqWithMaxLength(maxSeq,nextSeq);
			System.out.println("maxSeq: "+maxSeq);
			if(nextUnfit == currentUnfit) break;
			else currentUnfit = nextUnfit;
		}
		
	}
	/*public static ArrayList<HtWt> longestIncreasingSubsequence(ArrayList<HtWt> array){
		ArrayList<HtWt>[] solutions=new ArrayList<HtWt>[array.size()];
		
	}*/
	
	public static void main(String[] args) {
		//HtWt it1=new HtWt(60,100);
		//System.out.println(it1);
		
		//items.add(it1);
		items.add(new HtWt(60,100));
		//items.add(new HtWt(70,100));
		items.add(new HtWt(70,150));
		items.add(new HtWt(56,90));
		items.add(new HtWt(75,190));
		items.add(new HtWt(60,95));
		
		
		findMaxSeq();
		System.out.println("maxSeq: "+maxSeq);

	}

}
