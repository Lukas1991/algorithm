package fun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class StrobogrammaticNumber {

    //0,1,8, 69
    public boolean isStrobogrammatic(String num) {
        if (StringUtils.isEmpty(num)) return false;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,0);
        map.put(1,1);
        map.put(8,8);
        map.put(6,9);
        map.put(9,6);

        int i = 0, j = num.length() - 1;
        while (i <= j) {
            int start = num.charAt(i) - '0';
            int end = num.charAt(j) - '0';

            if (!map.containsKey(start) || map.get(start) != end) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public List<String> findStrobogrammatic(int n) {
        if(n <= 0) return new ArrayList<>();
        List<String>[] dp = new List[n+1];
        dp[0] = new ArrayList<>(Arrays.asList(""));
        dp[1] = new ArrayList<>(Arrays.asList("1", "0", "8"));

        for (int i=2; i<dp.length; i++) {
            dp[i] = new ArrayList<>();
            dp[i].addAll( addToSide(dp[i-2], i==n));
        }

        return dp[n];
    }

    private List<String> addToSide(List<String> pre, boolean addZero) {
        List<String> res = new ArrayList<>();
        pre.forEach(s -> {
            res.add("6" + s + "9");
            res.add("9" + s + "6");
            res.add("1" + s + "1");
            res.add("8" + s + "8");

            if(addZero) {
                res.add("0" + s + "0");
            }
        });
        return res;
    }

    //Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
    //For example,Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
    public int strobogrammaticInRange(String low, String high) {
        int n = high.length();
        List<String>[] dp = new List[n+1];
        dp[0] = new ArrayList<>(Arrays.asList(""));
        dp[1] = new ArrayList<>(Arrays.asList("1", "0", "8"));

        for (int i=2; i<dp.length; i++) {
            dp[i] = new ArrayList<>();
            dp[i].addAll( addToSide(dp[i-2], i==n));
        }

        int count = 0;

        //get low dp
        List<String> lowList = dp[low.length()];
        for(String s : lowList) {
            Integer num = Integer.valueOf(s);
            if (num >= Integer.valueOf(low)) {
                count ++;
            }
        }

        //get high dp
        List<String> highList = dp[high.length()];
        for(String s : highList) {
            Integer num = Integer.valueOf(s);
            if (num <= Integer.valueOf(high)) {
                count ++;
            }
        }

        //get the middle
        for (int i=low.length()+1; i<high.length(); i++) {
            count += dp[i].size();
        }

        return count;
    }

    public static void main(String[] args) {
        StrobogrammaticNumber obj = new StrobogrammaticNumber();

        System.err.println(obj.findStrobogrammatic(1));
        System.err.println(obj.findStrobogrammatic(2));
        System.err.println(obj.findStrobogrammatic(3));

        System.err.println(obj.strobogrammaticInRange("50", "100"));
//        System.err.println(obj.isStrobogrammatic("1"));
//        System.err.println(obj.isStrobogrammatic("7"));  //false
//        System.err.println(obj.isStrobogrammatic("69"));
//        System.err.println(obj.isStrobogrammatic("689"));
//        System.err.println(obj.isStrobogrammatic("1691"));
//        System.err.println(obj.isStrobogrammatic("1698"));  //false
    }
}
