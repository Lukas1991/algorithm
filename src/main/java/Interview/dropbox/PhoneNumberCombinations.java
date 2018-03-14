package Interview.dropbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PhoneNumberCombinations {

    public PhoneNumberCombinations() {
        digitMap = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        dict.add("dropbox");
        dict.add("drop");
        dict.add("box");

        dict.add("dr");
        dict.add("op");

        for (String word : dict) {
            insert(word);
        }
    }

    TrieNode root = new TrieNode();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();

        helper2(digits, 0, res, "", "");
        //helper3(digits, 0, res, "", "", root);
        return res;
    }

    private void helper(String[] dic, String digits, int start, String tmp, List<String> res) {
        if (start == digits.length()) {
            res.add(tmp);
        } else {
            char c = digits.charAt(start);
            int number = c - '0';
            String string = dic[number];

            for (int j = 0; j < string.length(); j++) {
                helper(dic, digits, start + 1, tmp + string.charAt(j), res);
            }
        }
    }

    //Followup 1, 求有效词组，比如一个号码拆分后也可以是｛ ‘drop’， ‘box’ ｝
    // 只有isWord
    //如果这个function 被heavily call，怎么办？hash map, 记忆华搜索
    private void helper2(String digits, int start, List<String> res, String tmp, String tmpRes) {
        if (start == digits.length()) {
            if (tmp.length() >= 3 && isWord(tmp)) {
                res.add(tmpRes + " " + tmp);
                return;
            }
        } else {
            String string = digitMap[digits.charAt(start) - '0'];

            for (int j = 0; j < string.length(); j++) {
                char c = string.charAt(j);
                String newWord = tmp + c;

                if (tmp.length() >= 3 && isWord(newWord)) {

                    String newTmpRes;
                    if (tmpRes.equals("")) {
                        newTmpRes = newWord;
                    } else {
                        newTmpRes = tmpRes + " " + newWord;
                    }

                    helper2(digits, start + 1, res, "", newTmpRes);
                }

                helper2(digits, start + 1, res, newWord, tmpRes);

            }

        }
    }

    //key - start index, value -  word break of s.substring(start)
    HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> word_Break(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        List<String> res = new ArrayList<>();

        for (int end = start + 1; end <= s.length(); end++) {
            String sub = s.substring(start, end);
            if (wordDict.contains(sub)) {
                List<String> list = word_Break(s, wordDict, end);

                for (String l : list) {
                    if (l.equals("")) {
                        res.add(sub);
                    } else {
                        res.add(sub + " " + l);
                    }
                }
            }
        }

        map.put(start, res);
        return res;
    }

    //key is string digits, value is
    HashMap<String, List<String>> cache = new HashMap<>();

    private List<String> helperMemSearch(String digits, int start, List<String> res, String tmp) {
        if (cache.containsKey(digits)) {
            return cache.get(digits);
        }

        String string = digitMap[digits.charAt(start) - '0'];
        List<String> tmpres = new ArrayList<>();

        for (int j = 0; j < string.length(); j++) {
            char c = string.charAt(j);
            String newWord = tmp + c;

            if (tmp.length() >= 3 && isWord(newWord)) {
                List<String> list = helperMemSearch(digits.substring(start + 1), 0, res, "");
                if (list.size() > 0) {
                    for (String s1 : list) {
                        tmpres.add(newWord + " " + s1);
                    }
                }
            }

            List<String> list = helperMemSearch(digits, start + 1, res, newWord);
            if (list.size() > 0) {
                tmpres.addAll(list);
            }
        }

        cache.put(digits.substring(0, start + 1), tmpres);
        return tmpres;
    }

    //isWord, isValidPrefix
    private void helperValidPrefix(String digits, int start, List<String> res, String tmp, String tmpRes) {
        if (start == digits.length()) {
            if (tmp.length() >= 3 && isWord(tmp)) {
                res.add(tmpRes + " " + tmp);
                return;
            }
        } else {
            String string = digitMap[digits.charAt(start) - '0'];

            for (int j = 0; j < string.length(); j++) {
                char c = string.charAt(j);
                String newWord = tmp + c;

                if (isValidPrefix(newWord)) {

                    if (tmp.length() >= 3 && isWord(newWord)) {
                        String newTmpRes;
                        if (tmpRes.equals("")) {
                            newTmpRes = newWord;
                        } else {
                            newTmpRes = tmpRes + " " + newWord;
                        }

                        helperValidPrefix(digits, start + 1, res, "", newTmpRes);
                    }

                    helperValidPrefix(digits, start + 1, res, newWord, tmpRes);
                }
            }
        }
    }

    //如果我能访问词典里所有词, build Trie, 边DFS边看trienode 含不含valid char
    private void helper3(String digits, int start, List<String> res, String tmp, String tmpRes, TrieNode node) {
        if (start == digits.length()) {
            if (tmp.length() >= 3 && node.word != null && node.word.equals(tmp)) {
                res.add(tmpRes + " " + tmp);
                return;
            }
        } else {
            String string = digitMap[digits.charAt(start) - '0'];

            for (int j = 0; j < string.length(); j++) {
                char c = string.charAt(j);

                if (node.children.containsKey(c)) {
                    if (tmp.length() >= 3 && node.children.get(c).word != null && node.children.get(c).word.equals(tmp + c)) {
                        String newTmpRes;
                        if (tmpRes.equals("")) {
                            newTmpRes = tmp + c;
                        } else {
                            newTmpRes = tmpRes + " " + tmp + c;
                        }
                        helper3(digits, start + 1, res, "", newTmpRes, root);
                    }


                    helper3(digits, start + 1, res, tmp + c, tmpRes, node.children.get(c));
                }
            }

        }
    }

    boolean isWord(String word) {
        Set<String> set = new HashSet<>();
        set.add("dropbox");
        set.add("drop");
        set.add("box");

        set.add("dr");
        set.add("op");
        return set.contains(word);
    }

    boolean isValidPrefix(String prefix) {
        Set<String> set = new HashSet<>();
        set.add("b");
        set.add("bo");
        set.add("box");

        set.add("d");
        set.add("dr");
        set.add("dro");
        set.add("drop");
        set.add("dropb");
        set.add("dropbo");
        set.add("dropbox");

        return set.contains(prefix);
    }

    class TrieNode {
        Map<Character, TrieNode> children;
        String word;

        public TrieNode() {
            children = new HashMap<>();
        }
    }

    String[] digitMap;
    Set<String> dict = new HashSet<>();

    public void insert(String word) {
        TrieNode curr = root;
        char[] arr = word.toCharArray();
        for (char c : arr) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }

        curr.word = word;
    }

    public static void main(String[] args) {
        String digits = "3767269";
        PhoneNumberCombinations obj = new PhoneNumberCombinations();


        List<String> res = obj.letterCombinations(digits);

        System.err.println(res.toString());
    }
}
