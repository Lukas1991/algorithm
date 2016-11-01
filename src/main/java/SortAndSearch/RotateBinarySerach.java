package SortAndSearch;

public class RotateBinarySerach {

	/**
	 * @param args
	 */
	public static int RotateSearch(int[] a,int start, int end,int find){
		while(start<=end){
			int mid=(start+end)/2;
			System.out.println("start,mid,end"+a[start]+a[mid]+a[end]);
			if(find==a[mid]) return mid;
			else if(a[start]<=a[mid]){
				if(find>a[mid]) start=mid+1;
				else if (find<a[mid] && find>=a[end]) end=mid-1;
				else if (find<a[mid] && find<a[end]) start=mid+1;
				
			}else if(a[start]>a[mid]){
				if(find<a[mid]) end=mid-1;
				 else if(find>a[mid] && find<=a[end]) start=mid+1;
				 else if(find>a[mid] && find>a[end]) end=mid-1;
			}
			
		}
		
		return -1;
	}
	
	public static int RotateSearch(int[] a,int find){
		
		return RotateSearch(a,0,a.length-1,find);
	}
	
	public static void main(String[] args) {
		int[] a={15,16,19,20,25,1,3,4,5,7,10,14};
		int pos=RotateSearch(a,19);
		System.out.println(pos);
	}

}
