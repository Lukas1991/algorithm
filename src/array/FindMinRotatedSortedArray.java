package array;

import java.util.Arrays;

public class FindMinRotatedSortedArray {

	/**[4,5,6,7,0,1,2]
	 * If we pick the middle element (7), we can compare the middle element with the leftmost element (4).
	 *  
	 */
	
	public int findMinRecursive(int[] num) {
        return findMinRecursive(num, 0, num.length - 1);
    }
 
	public int findMinRecursive(int[] num, int left, int right) {
		if (left == right)
			return num[left];
		if ((right - left) == 1)
			return Math.min(num[left], num[right]);
	 
		int middle = (left + right) / 2;
	 
		// not rotated
		if (num[left] < num[right]) {
			return num[left];
	 
		// go right side
		} else if (num[middle] > num[left]) {
			return findMinRecursive(num, middle+1, right);
	 
		// go left side
		} else {
			return findMinRecursive(num, left, middle);
		}
	}
	
	public int findMinHasDuplicateRecursive(int[] num, int left, int right) {
	    
        if (left == right)
    		return num[left];
    	if ((right - left) == 1)
    		return Math.min(num[left], num[right]);
     
    	int middle = (left + right) / 2;
     
    	// not rotated
    	if (num[left] < num[right]) {
    		return num[left];
    	} else if (num[left] == num[right]) {
    		return findMinHasDuplicateRecursive(num, left+1, right);
    	// go right side
    	} else if (num[middle] >= num[left]) {
    		return findMinHasDuplicateRecursive(num, middle+1, right);
     
    	// go left side
    	} else {
    		return findMinHasDuplicateRecursive(num, left, middle);
    	}
         
	}
	
	
	
	//while loop:
	public int findMin(int[] num) {
        int left = 0, right = num.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            
            if (num[left] < num[right]) return num[left];
            
            if (num[mid] >= num[left]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return num[left];
    }
	
	public int findMinHasDuplicate(int[] num) {
        
        int left = 0, right = num.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (num[left] < num[right]) return num[left];
           
            if (num[mid] > num[left]) {
                left = mid + 1;
            } else if (num[mid] < num[left]) {
                right = mid;
            } else {
                left++;
            }
        }
        
        return num[left];
    }
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {0,1,2,3,4};
		A = Arrays.copyOf(A, 2);
		for(int e: A){
			System.out.println(e);
		}
		
	}

}
