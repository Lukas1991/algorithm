package combination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneNumber {

	public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        
        Map<Character, String> map = getMap();
        
        for(int i=0; i<digits.length(); i++) {
            char digit = digits.charAt(i);
            String letters = "";
            if (map.containsKey(digit)) {
                letters = map.get(digit);
            }
            
            if (letters.isEmpty()) {
                continue;
            } else {
                List<String> newList = new ArrayList<>();
                if (res.isEmpty()) {
                    res.add("");
                }
            
                for(int j=0; j<letters.length(); j++) {
                    for(String str : res) {
                        str = str + letters.charAt(j);
                        newList.add(str);
                    }
                }
            
                res = newList;
            }
            
        }
        
        return res;
    }
	
	private Map<Character, String> getMap() {
        Map<Character, String> map = new HashMap<>();
        map.put('1', "");
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        return map;
    }
	
	public static void main(String[] args) {
		PhoneNumber clazz = new PhoneNumber();
		List<String> res = clazz.letterCombinations("2019");
		System.err.println("size is : " + res.size());
		for(String s : res) {
			System.err.println(s);
		}
	}
}
