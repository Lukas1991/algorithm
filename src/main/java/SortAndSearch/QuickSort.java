package SortAndSearch;

import java.util.Arrays;

/**
 * 
 * 
 *
 */
public class QuickSort {

	public int[] quickSort(int arr[]) {
		return quickSort(arr,0,arr.length-1);
	}
	
	public int[] quickSort(int arr[],int start,int end){
		int size=end-start+1;
		//System.out.format("start: %d end: %d size %d%n",start,end,size);
		if(size<=1) {return arr;}
		
		int L=start-1,U=end+1;
		//select a pivot randomly
		//int pivotIndex=(int)(Math.random())*10%size;
		int pivotIndex=size/2;
		int pivot=arr[start+pivotIndex];
		System.out.print("pivot is: "+pivot+" pivotIndex "+(start+pivotIndex)+"\n");
		while(L<U){
			L++;
			while(arr[L]<pivot) L++;
			U--;
			while(arr[U]>pivot) U--;
			
			//swap 		
			//System.out.print("swap: "+arr[L]+" "+arr[U]+"\n");
			if(L<U){
				int temp=arr[L];arr[L]=arr[U];arr[U]=temp;
			}
			
			for(int a:arr)	System.out.print(a+" ");System.out.print("\n");			
		}
		System.out.print("L:"+L+" U:"+U+"\n");
		
		quickSort(arr,start,U-1);
		quickSort(arr,U+1,end);
		
		return arr;
	    
	}
	public static void main(String[] args) {
		QuickSort qs=new QuickSort();
		
		/*Random generator = new Random();
		
		int[] a = new int[10];
		for (int i = 0; i < a.length; i++)
			a[i] = generator.nextInt(100);
		
		System.out.println(Arrays.toString(a));
		*/
		int[] arr={5,86,69,73,69,19,34,20,74,3};
		//int[] arr={3,1,4,5,9,2,6,8,7,10};
		/*int[] arr=new int[10];
		for(int i=0;i<10;i++){
			arr[i]=a[i];
		}*/
		
		int[] sorted=qs.quickSort(arr);
		System.out.println(Arrays.toString(sorted));
		
		/*System.out.println(Arrays.toString(a));
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));*/
		

	}

}
