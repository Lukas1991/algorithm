package SortAndSearch;

public class MergeBintoA {

	/**
	 * @param args
	 */
	public static int[] merge(int[] a,int[] b,int m,int n){
		int k=m+n-1;
		int i=m-1;
		int j=n-1;
		
		//check from the end of each array
		while(i>=0 && j>=0){
			if(a[i]>b[j]){
				a[k]=a[i];
				k--;i--;
			}else{
				a[k]=b[j];
				k--;j--;
			}
		}
		
		while(j>=0){
			a[k]=b[j];
			k--;j--;
		}
		return a;
		
	}
	
	public static void main(String[] args) {
		int[] a=new int[20];
		a[0]=1;a[1]=3;a[2]=7;a[3]=9;
		int[] b={2,4,5,6};
		
		int[] result=merge(a,b,4,4);
		for(int i:result) System.out.print(i);
		System.out.println();
		
	}

}
