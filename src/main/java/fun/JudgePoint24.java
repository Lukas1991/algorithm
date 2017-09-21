package fun;

import java.util.ArrayList;
import java.util.List;

public class JudgePoint24 {
	
	public boolean judgePoint24(int[] nums) {
		List<Double> list = new ArrayList<>();		
        for (int v: nums) {
        	list.add((double) v);
        }
        
        return solve(list);
    }
	
	
	/**
	 * 4个数选两个，加减乘除，结果和剩下两个数并到一起，A42 = 12，12*4种可能，还剩三个数。
	 * 3个数选两个，加减乘除，结果和剩下的一个并到一起，A32 = 6， 6*4种可能，还剩两个数。 
	 * 2个数加减乘除。 A22 = 2， 2*4种可能
	 * 总共 12*4 * 6*4 * 2*4 = 9216种可能。
	 */
    private boolean solve(List<Double> nums) {
        if (nums.size() == 0) return false;
        if (nums.size() == 1) return Math.abs(nums.get(0) - 24) < 1e-6;

        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i != j) {
                	List<Double> list2 = new ArrayList<>();
                	
                	//add the rest in nums to list2, not i, not j, 
                    for (int k = 0; k < nums.size(); k++) {
                    	if (k != i && k != j) {
                        	list2.add(nums.get(k));
                        }     	
                    }
                    
                    // i and j +-*/               
                    for (int k = 0; k < 4; k++) {
                        if (k < 2 && j > i) continue; //a+b, a*b are commutative 交换律
                        
                        if (k == 0) list2.add(nums.get(i) + nums.get(j));
                        if (k == 1) list2.add(nums.get(i) * nums.get(j));
                        if (k == 2) list2.add(nums.get(i) - nums.get(j));
                        if (k == 3) {
                            if (nums.get(j) != 0) {
                            	list2.add(nums.get(i) / nums.get(j));
                            } else {
                                continue;
                            }
                        }
                        
                        if (solve(list2)) return true;
                        list2.remove(list2.size() - 1);
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JudgePoint24 obj = new JudgePoint24();
        System.err.println(obj.judgePoint24(new int[]{4, 1, 8, 7}));  //t
        System.err.println(obj.judgePoint24(new int[]{1, 2, 1, 2}));  //f

        System.err.println(obj.judgePoint24(new int[]{1, 3, 4, 6}));  //true
        System.err.println(obj.judgePoint24(new int[]{1, 5, 9, 1}));  //false

        System.err.println(obj.judgePoint24(new int[]{3, 9, 7, 7}));  //true
        System.err.println(obj.judgePoint24(new int[]{1, 9, 1, 2}));  //true
    }
}
