package array;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

	public int[] twoSum(int[] numbers, int target) {
		//Use HashMap to store the target value.
		//Time complexity of this solution: O(n).
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			int[] result = new int[2];
		 
			for (int i = 0; i < numbers.length; i++) {
				if (map.containsKey(numbers[i])) {
					int index = map.get(numbers[i]);
					result[0] = index+1 ;
					result[1] = i+1;
					break;
				} else {
					map.put(target - numbers[i], i);
				}
			}
		 
			return result;
		   
		    }
	
	 public int[] twoSumOld(int[] numbers, int target) {
	       
		 int[] copyNum=Arrays.copyOfRange(numbers,0,numbers.length);
	        
	        Arrays.sort(copyNum);
	        if(copyNum==null || copyNum.length<2) return new int[]{-1,-1};
	        
	        int i=0,j=copyNum.length-1;
	        boolean find=false;
	        
	        while(i<j){
	            int sum=copyNum[i]+copyNum[j];
	            if(sum==target){
	                find=true;
	                break;
	            }else if(sum<target){
	                i++;
	            }else{
	                j--;
	            }
	            
	        }
	        
	      //not find
	        if(!find) return new int[]{-1,-1};
	        //number is copyNum[i],copyNum[j]
	        //find index
	        int x=0,y=numbers.length-1;
	        for(x=0;x<numbers.length;x++){
	            if(numbers[x]==copyNum[i]) break;
	        }
	        
	        for(;y>0;y--){
	            if(numbers[y]==copyNum[j]) break;
	        }
	        //find
	        if(x>y){
	            return new int[]{y+1,x+1};
	        }else if(x<y){
	            return new int[]{x+1,y+1};
	        }else
	            return new int[]{-1,-1};
	        
    
	        
	    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}