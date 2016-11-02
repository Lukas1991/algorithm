package SortAndSearch;

public class BinarySearchString {

	public static int search(String[] a,int start,int end,String find){
		if(find==""){
			for(int i=0;i<a.length;i++){
				if(a[i]=="") return i;
			}
			return -1;
		}
		
		while(start<=end){
			while(a[end]=="") end--;
			System.out.println("end: "+end);
			
			if(end<start) return -1;
			
			int mid=(start+end)/2;
			//when hit an empty string, advance to the next non-empty string.
			while(a[mid]=="") mid++;
			System.out.println("mid: "+a[mid]);
			
			int r=a[mid].compareTo(find);
			if(r==0) return mid;
			else if(r<0) start=mid+1;
			else end=mid-1;
			
		}
		
		return -1;
	}
	public static void main(String[] args) {
		String[] a={"at","","","ate","","","","ball","","","","",""};
		String find="car";
		int pos=search(a,0,a.length-1,find);
		System.out.println(pos);
	}

}
