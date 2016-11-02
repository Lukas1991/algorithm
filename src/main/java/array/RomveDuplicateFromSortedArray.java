package array;

public class RomveDuplicateFromSortedArray {

	/**
	 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
	 * Do not allocate extra space for another array, you must do this in place with constant memory.
	 * For example,Given input array A = [1,1,2],Your function should return length = 2, and A is now [1,2].
	 */
	 public int removeDuplicates(int[] A) {
	        if(A.length < 2) return A.length;
	        
	        int tail=0;
	        for(int i=1; i<A.length; i++){
	            if(A[i] == A[i-1]){
	                //continue;
	            }else{
	                tail++;
	                A[tail]=A[i];
	            }
	            
	        }
	        
	        return tail+1;
	        
	        
	    }
	
	/**
	 * What if duplicates are allowed at most twice?
	 * A = [1,1,1,2,2,3], should return length = 5, and A is now [1,1,2,2,3].
	 */
	public int removeDuplicates2(int[] A) {
        if(A.length <= 2) return A.length;
       
       int tail=1;
       
       for(int i=2; i<A.length; i++){
           if(A[i] == A[tail] && A[i] == A[tail-1]){
               //continue;
           }else{
               tail++;
               A[tail]=A[i];
           }
           
       }
       
       return tail+1;
   }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}