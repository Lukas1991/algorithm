package SortAndSearch;
import java.util.Arrays;
import java.util.Random;


public class BinarySearch {

	public static int binarySearch(int[] a,int start, int end,int find){
		int middle=start;
		while(start<=end){
			middle=(start+end)/2;
			if(find==a[middle]) return middle;
			if(find>a[middle]) start=middle+1;
			if(find<a[middle]) end=middle-1;
		}
		return -1;
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random generator = new Random();
		
		/*int[] a = new int[10];
		for (int i = 0; i < a.length; i++)
			a[i] = generator.nextInt(100);*/

		int[] a={3, 5, 19, 20, 34, 69, 73, 74, 86};
		System.out.println(Arrays.toString(a));
		int pos=binarySearch(a,0,a.length-1,86);
		System.out.println(pos);
		
		int pos2=Arrays.binarySearch(a, 86);
		System.out.println(pos2);

	}

}
