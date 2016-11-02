package array;

public class GasStation {

	/**
	 * 1) if total of gas[] >= total of cost[], then there exists a start index to complete the circle.
	 * 2) if A can not reach C in a the sequence of A-->B-->C, then B can not make it either. use C as a new start
	 * @param args
	 */
	public int canCompleteCircuit(int[] gas, int[] cost) {
        
        if(gas.length==0 || cost.length == 0 ||gas.length != cost.length){
            return -1;
        }
        int start=0,total=0,sum=0;
        for(int i=0; i<gas.length; i++){
            sum += gas[i]-cost[i];
            if(sum<0){
                sum = 0;
                start = i+1;
            }
            total += gas[i]-cost[i];
           
        }
        
        if(total >= 0) return start;
        else return -1;
        
    }
	
	//O(n^2)
public static int canCompleteCircuit2(int[] gas, int[] cost) {
        
        if(gas.length==0 || cost.length == 0 ||gas.length != cost.length){
            return -1;
        }
        
        for(int i=0; i<gas.length; i++){
            int left = gas[i];
            boolean flag = true;
            int j=i;
            while(flag){
                left = left - cost[j%gas.length];
                if(left>=0){//can travel to the next
                    j++;
                    int newIndex = j%gas.length;
                    left = left + gas[newIndex];
                    if(newIndex == i){
                        return i;
                        //flag = false;
                    } 
                    
                }else{
                    flag = false;
                }
            }
            
           
        }
        
        return -1;
        
    }
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
