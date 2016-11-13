package string;

import java.util.Arrays;

public class Anagram {

    //if the inputs contain unicode characters, use a map, because there are 128000 unicode charcaters
    //if inputs contain ASCII, int[256]
    //if inputs contain only, lowercase alphabets, int[26]
    public boolean isAnagramArray(String s, String t) {
        int[] set = new int[26];

        for (int i=0; i<s.length(); i++) {
            int num = s.charAt(i) - 'a';
            set[num]++;
        }

        for (int i=0; i<t.length(); i++) {
            int num = t.charAt(i) - 'a';
            set[num]--;
        }

        for (int count : set) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isAnagramASCII(String s, String t) {
        int[] set = new int[256];

        for (int i=0; i<s.length(); i++) {
            int num = s.charAt(i);
            set[num]++;
        }

        for (int i=0; i<t.length(); i++) {
            int num = t.charAt(i);
            set[num]--;
        }

        for (int count : set) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isAnagramSort(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();

        Arrays.sort(s1);
        Arrays.sort(t1);
        return Arrays.equals(s1, t1);
    }
}
