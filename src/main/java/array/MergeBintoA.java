package array;
/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 *
 */

/**
 * The key to solve this problem is moving element of A and B backwards. 
 * If B has some elements left after A is done, also need to handle that case.
 * O(m+n)
 *
 */
public class MergeBintoA {

	 public void merge(int A[], int m, int B[], int n) {
	        
	        int i=m-1;
	        int j=n-1;
	        int k=m+n-1;
	        while(i>=0 && j>=0){
	            if(A[i]>=B[j]){
	                A[k]=A[i];
	                i--;
	            }else{
	                A[k]=B[j];
	                j--;
	            }
	            k--;
	        }
	        //j=0 leave A
	        //i=0 put the rest of B into A
	        while(j>=0){
	             A[k]=B[j];
	                j--;
	                k--;
	        }
	        
	    }
	
}
