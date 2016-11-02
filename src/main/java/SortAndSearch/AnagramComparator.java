package SortAndSearch;

import java.util.Arrays;
import java.util.Comparator;

public class AnagramComparator implements Comparator<String>{

	public static String sortChars(String s){
		char[] arr=s.toCharArray();
		Arrays.sort(arr);
		String newStr=new String(arr);	
		return newStr;
		
	}
	
	public int compare(String s1,String s2){
		return sortChars(s1).compareTo(sortChars(s2));
	}
	
	public static void main(String[] args) {
		String[] arr={"bbb","cat","act","tac"};
		Arrays.sort(arr, new AnagramComparator());
		for(String s:arr) System.out.println(s);
		
		System.out.println();
	}

}
