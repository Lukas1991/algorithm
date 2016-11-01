package array;

public class FindMissingPositive {

	/**
	 * @param args
	 */
	 public int firstMissingPositive(int[] A) {
	        int len = A.length;
	        int i=0;
	        
	        //put A[i] in the place index=A[i]-1, so A[i] should equels to index+1, if not, swap
	        //if A[i] == A[A[i]-1], another duplicate values there, don't need to swap
	        while(i<len){
	        	if(A[i]>0 && A[i]<=len && A[i]!=(i+1) && A[i]!=A[A[i]-1]){
	        		swap(i, A[i]-1, A);
	        	}else{
	        		i++;
	        	}
	        }
	        
	        for(int j=0; j<len; j++){
	        	if(A[j] != j+1)
	        		return j+1;
	        }
	        
	        return len+1;
	    }
	    
	    public void swap(int i, int j, int[] A){
	        int temp = A[i];
	        A[i] = A[j];
	        A[j] = temp;
	    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr={-2, -100, 9, 1, 3};
		FindMissingPositive obj = new FindMissingPositive();
		System.out.println(obj.firstMissingPositive(arr));
		
		
	}

}
