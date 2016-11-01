package SortAndSearch;

public class BubbleSort {

	/**
	 * @param args
	 */

	/*
	 * Bubble Sort: start from the begining of the list, compare every adjacent pair, 
	 * swap their position if they are not in the right order.
	 * After each iteration, one less element is needed to be compared until there are
	 * no more elements left to be compared.
	 */
	public static int[] bubbleSort(int[] arr){
		int size=arr.length;
		for(int i=0;i<size;i++){
			//find the largest
			for(int j=0;j<size-i-1;j++){
				if(arr[j]>arr[j+1]){
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
		
		return arr;
		
	}
	
	/*Insertion Sort:
	 * March up the array, check each element. if larger than the previous position checked,
	 * leave it. If smaller then march back down, shifting the larger elements up until 
	 * encounter a smaller element. Insert there.
	 */
	public static int[] insertionSort(int[] arr){
		int n=arr.length;
		for(int i=0;i<n;i++){
			for(int j=i;j>0;j--){
				if(arr[j]<arr[j-1]){
					int temp=arr[j];
					arr[j]=arr[j-1];
					arr[j-1]=temp;
				}
			}
			
			}
		return arr;
		}
	/* Selection Sort:
	 * Run through the array to find the smallest one. Swap it with the first element
	 * so that the smallest entry is in the first position.
	 * Next run through the remaining entries to find the next smallest element, and 
	 * swap it into the second position. Continue this procedure of filling the next 
	 * position with the next smallest entry for the rest of the array.
	 */
	public static int[] selectionSort(int[] arr){
		int n=arr.length;
		int index=0;
		for(int i=0;i<n;i++){
			index=i;
			for(int j=i;j<n;j++){				
				if(arr[j]<arr[index]) index=j;
			}
			int temp=arr[i];
			arr[i]=arr[index];
			arr[index]=temp;
					
		}
		return arr;
		
		
	}

	
	
	public static void main(String[] args) {
		
		int[] arr={5,9,7,4,6};
		
		for(int a:arr)	System.out.print(a+" ");
		
		System.out.println("\n");
		//int[] newArr=bubbleSort(arr);
		//int[] newArr=insertionSort(arr);
		//int[] newArr=selectionSort(arr);
		//for(int a:newArr)	System.out.print(a+" ");
		
		
		
	}

}
