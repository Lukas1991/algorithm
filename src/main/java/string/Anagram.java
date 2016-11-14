package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Anagram {

    //438. Find All Anagrams in a String
    public List<Integer> findAnagramsSlidingWindow(String s, String p) {
        int sSize = s.length(), pSize = p.length();
        List<Integer> res = new ArrayList<>();
        if(sSize < pSize) {
            return res;
        }

        int[] set = new int[26];
        for (int i=0; i<pSize; i++) {
            int num = p.charAt(i) - 'a';
            set[num]++;
        }

        int[] tmp = new int[26];
        //put s first substring to tmp
        for (int i=0; i<pSize; i++) {
            tmp[s.charAt(i) - 'a']++;
        }

        if (check(set, tmp)) {
            res.add(0);
        }

        //i is the right side of the new window
        for (int i=pSize; i<sSize; i++) {
            tmp[s.charAt(i-pSize) - 'a']--; //left side of the old window
            tmp[s.charAt(i) - 'a']++;

            if (check(set, tmp)) {
                res.add(i-pSize + 1); //left side of the new window
            }
        }

        return res;
    }

    private boolean check(int[] set, int[] tmp) {
        for (int i=0; i<set.length; i++) {
            if (set[i] != tmp[i]) {
                return false;
            }
        }
        return true;
    }


    public List<Integer> findAnagrams(String s, String p) {
        int size = p.length();
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<=s.length() - size; i++) {
            String sub = s.substring(i, i+size);
            if (isAnagramArray(sub, p)) {
                res.add(i);
            }
        }

        return res;
    }


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
