package math;

import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {

	public static String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];        
    }
	
	public static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100); 
        map.put("D", 500);
        map.put("M", 1000);

        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        
        int res = 0;
        for (int i = 0; i<s.length(); i++) {
            if (i + 1 < s.length() && map.containsKey(s.substring(i, i+2))) {
                res += map.get(s.substring(i, i+2));
                i++;
            } else {
                res += map.get(s.substring(i, i+1));
            }
        }
        return res;
    }
	
	public static void main(String[] args) {
		int a = 3999;
		String s = intToRoman(a);
		System.err.println(s);
		System.err.println(romanToInt(s));

	}

}
