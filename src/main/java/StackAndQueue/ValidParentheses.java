package StackAndQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //is open bracket
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                //is closed bracket
                if (stack.isEmpty() || map.get(stack.pop()) != c) {
                    return false;
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 678. Valid Parenthesis String. Given a string containing only three types of characters: '(', ')' and '*', write a function
     * to check whether this string is valid. '*' could be treated as a single right parenthesis ')' or a single left parenthesis
     * '(' or an empty string.
     *
     * lo is the smallest possible number of open left brackets after processing the current character。 最少可能有多少个open '(', 没陪对儿的'('
     * hi is the largest possible number of open left brackets. 最多可能有多少个open '('
     *
     * Time: O(N) Space: O(1)
     */
    public boolean checkValidStringGreedy(String s) {
        if (s.length() == 0) {
            return true;
        }
        int lo = 0;
        int hi = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                lo++;
            } else {
                lo--; // ')'和 '*' 都按')'算, open '(' 就少一个
            }

            if (c == ')') {
                hi--;
            } else {
                hi++;// '('和 '*' 都按'('算, open '(' 就多一个
            }

            if (hi < 0) {
                return false; //已经遇到多余的')'
            }

            lo = Math.max(lo, 0); //lo 不可能为复数,最小为0
        }

        return lo == 0; //最后检查不能有多余的'('
    }

    //Time : O(3^N), N is s length
    public boolean checkValidStringRecursion(String s) {
        if (s.length() == 0) {
            return true;
        }
        return check(s, 0, 0, 0);
    }

    private boolean check(String s, int i, int left, int right) {
        if (i == s.length()) {
            return left == right;
        }
        char c = s.charAt(i);
        if (c == '*') {
            return check(s, i + 1, left + 1, right) || check(s, i + 1, left, right + 1) || check(s, i + 1, left, right);
        } else if (c == '(') {
            return check(s, i + 1, left + 1, right);
        } else {
            if (left <= right) {
                return false;
            } else {
                return check(s, i + 1, left, right + 1);
            }
        }
    }
}
