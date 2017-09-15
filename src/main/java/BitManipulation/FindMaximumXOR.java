package BitManipulation;

import java.util.HashSet;
import java.util.Set;

public class FindMaximumXOR {

    //利用XOR的性质，a^b = c, 则有 a^c = b，且 b^c = a;
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 5; i >= 0; i--) {
            //每次mask加一位1
            mask = mask | (1 << i);
            //System.err.println("mask: " + Integer.toBinaryString(mask));

            Set<Integer> set = new HashSet<>();
            //add prefix [32-i] bits to set
            for(int num : nums){
                set.add(num & mask);
            }

            //System.err.println("max: " + Integer.toBinaryString(max));
            //i位可以设为1或者0, 设为1是假设当前所能达到的最大值是这个temp值；
            int tmp = max | (1 << i);
            //System.err.println("tmp: " + Integer.toBinaryString(tmp));

            //假定任意两数xor能得到的最大值是tmp,那么他一定满足a^b = tmp,其中set.contains(a) && set.contains(b). 所以，我们只需要判断b^tmp的结果是不是在set内，就可以知道这个tmp能不能又这个set中的任意两个数组成。
            for(int prefix : set) {
                int test = tmp ^ prefix;
                if(set.contains(test)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        FindMaximumXOR obj = new FindMaximumXOR();
        int res = obj.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8});
        System.err.println(res);
    }

}
