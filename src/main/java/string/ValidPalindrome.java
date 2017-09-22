package string;

public class ValidPalindrome {

    /**
     * 125. Valid Palindrome Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * "A man, a plan, a canal: Panama" is a palindrome.
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        s = s.toUpperCase();

        int i = 0;
        int j = s.length() - 1;
        char[] arr = s.toCharArray();

        while (i <= j) {
            if (isValid(arr[i]) && isValid(arr[j])) {
                if (arr[i] != arr[j]) {
                    return false;
                } else {
                    i++;
                    j--;
                }
            } else {
                if (!isValid(arr[i])) {
                    i++;
                }

                if (!isValid(arr[j])) {
                    j--;
                }
            }
        }

        return true;
    }

    private boolean isValid(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    /**
     * 680. Valid Palindrome II Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
     * Input: "abca", Output: True, You could delete the character 'c'
     */
    public boolean validPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start <= end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                if (isP(s, start, end - 1) || isP(s, start + 1, end)) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isP(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome obj = new ValidPalindrome();
        System.err.println(obj.validPalindrome("abca"));
    }

}
