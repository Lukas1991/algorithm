package Interview.dropbox;

import java.util.*;

public class PhoneNumberCombinations {
    String[] digitMap;
    Set<String> dict = new HashSet<>();

    public PhoneNumberCombinations() {
        digitMap = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        dict.add("dropbox");
        dict.add("drop");
        dict.add("box");

        dict.add("dro");
        dict.add("pbox");

        dict.add("dr");
        dict.add("op");

        dict.add("d");
        dict.add("r");
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
        helper(digits, 0, "", res);
        return res;
    }

    private void helper(String digits, int start, String tmp, List<String> res) {
        if (start == digits.length()) {
            if (isWord(tmp)) { //check is in dict
                res.add(tmp);
            }
        } else {
            String string = digitMap[digits.charAt(start) - '0'];

            for (int j = 0; j < string.length(); j++) {
                helper(digits, start + 1, tmp + string.charAt(j), res);
            }
        }
    }

    //<---------------------------------Followup 1-----digits长度为7-------min word len is 3-------------------------------------------------------------------------------------->
    //求有效词组，比如一个号码拆分后也可以是｛'drop', 'box'｝同时知道字典里的词最小长度是3。那么只有两种情况，3+4或者4+3，或者7
    public Set<String> letterCombinationsLen7(String digits) {
        if (digits == null || digits.length() == 0) {
            return new HashSet<>();
        }
//
//        if (cache.containsKey(digits)) {
//            return cache.get(digits);
//        }
        Set<String> res = new HashSet<>();

        for (int i = 3; i <= digits.length() - 3; i++) {
            String left = digits.substring(0, i);
            String right = digits.substring(i);

            Set<String> leftResult = letterCombinationsLen7(left);
            Set<String> rightResult = letterCombinationsLen7(right);

            res.addAll(merge(leftResult, rightResult));
        }

        if (digits.length() >= 3) {
            //helper(digits, 0, "", res);
            //helperValidPrefix(digits, 0, "", res);
            helperTrie(digits, 0, "", res, root);
        }

        //cache.put(digits, res);
        return res;
    }

    //<----------------Followup 2  digits 长度不限------------------min word len is 3------------------------------------------------------------------------------------------------->
    //key is string digits, value is combination result
    HashMap<String, Set<String>> cache = new HashMap<>();

    /**
     * 电话长度不为7
     * 记忆化搜索.
     * set 去重复
     * 不同方法letterCombinationsAnyLen，letterCombinationsLen7 用不同的cache。
     */
    public Set<String> letterCombinationsAnyLen(String digits) {
        if (digits == null || digits.length() == 0) {
            return new HashSet<>();
        }

        if (cache.containsKey(digits)) {
            return cache.get(digits);
        }
        Set<String> res = new HashSet<>();

        for (int i = 3; i <= digits.length() - 3; i++) {
            String left = digits.substring(0, i);
            String right = digits.substring(i);

            Set<String> leftList = letterCombinationsAnyLen(left);
            Set<String> rightList = letterCombinationsAnyLen(right);

            res.addAll(merge(leftList, rightList));
        }

        if (digits.length() >= 3) {
            //helper(digits, 0, "", res);
            //helperValidPrefix(digits, 0, "", res);
            helperTrie(digits, 0, "", res, root);
        }

        cache.put(digits, res);
        return res;
    }

    //<----------------Followup 2----digits 长度不限---------------------min word len is 1---------------------------------------------------------------------------------------------->
    public Set<String> letterCombinationsWordAnyLen(String digits) {
        if (digits == null || digits.length() == 0) {
            return new HashSet<>();
        }

        if (cache.containsKey(digits)) {
            return cache.get(digits);
        }
        Set<String> res = new HashSet<>();

        for (int i = 1; i < digits.length(); i++) {
            String left = digits.substring(0, i);
            String right = digits.substring(i);

            Set<String> leftList = letterCombinationsWordAnyLen(left);
            Set<String> rightList = letterCombinationsWordAnyLen(right);

            res.addAll(merge(leftList, rightList));
        }

        if (digits.length() >= 1) {
            //helper(digits, 0, "", res);
            //helperValidPrefix(digits, 0, "", res);
            helperTrie(digits, 0, "", res, root);
        }

        cache.put(digits, res);
        return res;
    }

    //<---没有优化的-------------
    private void helper(String digits, int start, String tmp, Set<String> res) {
        if (start == digits.length()) {
            if (isWord(tmp)) {
                res.add(tmp);
            }
        } else {
            String string = digitMap[digits.charAt(start) - '0'];

            for (int j = 0; j < string.length(); j++) {
                helper(digits, start + 1, tmp + string.charAt(j), res);
            }
        }
    }

    //<---优化-------------如果给了API，isValidPrefix--------------------
    private void helperValidPrefix(String digits, int start, String tmp, Set<String> res) {
        if (start == digits.length()) {
            if (isWord(tmp)) {
                res.add(tmp);
            }
        } else {
            String string = digitMap[digits.charAt(start) - '0'];

            for (int j = 0; j < string.length(); j++) {
                if (isValidPrefix(tmp + string.charAt(j))) { //check prefix
                    helperValidPrefix(digits, start + 1, tmp + string.charAt(j), res);
                }
            }
        }
    }

    //<-----优化-------------如果我能访问词典里所有词, build Trie----------------------------------------------------------------------------------------------------------------->
    //边DFS边看trieNode 含不含valid char
    private void helperTrie(String digits, int start, String tmp, Set<String> res, TrieNode node) {
        if (start == digits.length()) {
            if (node.word != null && node.word.equals(tmp)) {
                res.add(tmp);
            }
        } else {
            String string = digitMap[digits.charAt(start) - '0'];

            for (int j = 0; j < string.length(); j++) {
                char c = string.charAt(j);
                if (node.children.containsKey(c)) {
                    helperTrie(digits, start + 1, tmp + c, res, node.children.get(c));
                }
            }
        }
    }

    Set<String> merge(Set<String> leftList, Set<String> rightList) {
        Set<String> res = new HashSet<>();
        for (String left : leftList) {
            for (String right : rightList) {
                res.add(left + " " + right);
            }
        }
        return res;
    }

    boolean isWord(String word) {
        return dict.contains(word);
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
        PhoneNumberCombinations obj = new PhoneNumberCombinations();
        //List<String> res = obj.letterCombinations("3767269");

//        Set<String> res = obj.letterCombinationsLen7("3767269");
//        System.err.println(res.toString());
//        System.err.println(res.size());

//        Set<String> res = obj.letterCombinationsAnyLen("37672693767269");
//        System.err.println(res.toString());
//        System.err.println(res.size());

        Set<String> res = obj.letterCombinationsWordAnyLen("37672693767269");
        System.err.println(res.toString());
        System.err.println(res.size());
    }
}
