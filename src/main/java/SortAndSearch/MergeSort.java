package SortAndSearch;
import java.util.Arrays;
import java.util.Random;


public class MergeSort {

	
	public static int[] mergeSort(int[] arr){
		int n=arr.length;
		if(n<=1){
		    return arr;
		  }
		int[] L=new int[n/2];
		int[] R=new int[n-n/2];
		
		for(int i=0;i<n/2;i++){L[i]=arr[i];}
		for(int i=0;i<(n-n/2);i++){R[i]=arr[n/2+i];}
		L=mergeSort(L);
		R=mergeSort(R);
		return merge(L,R);
		
		
	}
	public static int[] merge(int[] L,int[] R){
		int l1=L.length,l2=R.length;
		int[] temp=new int[l1+l2];
		int i=0,j=0,k=0;
		
		while(i<l1 && j<l2){
			if(L[i]<R[j]){
				temp[k]=L[i];
				i++;k++;
			}else{
				temp[k]=R[j];
				j++;k++;
			}
		}
		
		while(i<l1){
			temp[k]=L[i];
			i++;k++;
		}
		while(j<l2){
			temp[k]=R[j];
			j++;k++;
		}		
		return temp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random generator = new Random();
		
		int[] a = new int[10];
		for (int i = 0; i < a.length; i++)
			a[i] = generator.nextInt(100);
		
		System.out.println(Arrays.toString(a));
		
		int[] newa=mergeSort(a);
		System.out.println(Arrays.toString(newa));
		
	}

}
