package string;

public class PalindromeStream {

    StringBuilder firstHalf = new StringBuilder();
    StringBuilder secondHalf = new StringBuilder();  //reverse of second half string
    Character mid = null;

    public boolean isPalindrome(char c) {
        if (mid == null) {
            if (firstHalf.length() == 0) {
                mid = c;
                return true;
            } else {
                mid = secondHalf.charAt(secondHalf.length() - 1);
                secondHalf.deleteCharAt(secondHalf.length() - 1);
                secondHalf.insert(0, c);
                return firstHalf.toString().equals(secondHalf.toString());
            }

        } else {
            firstHalf.append(mid);
            mid = null;
            secondHalf.insert(0, c);
            return firstHalf.toString().equals(secondHalf.toString());
        }
    }

    public static void main(String[] args) {
        PalindromeStream obj = new PalindromeStream();
        System.err.println(obj.isPalindrome('a'));
        System.err.println(obj.isPalindrome('b'));
        System.err.println(obj.isPalindrome('c'));
        System.err.println(obj.isPalindrome('b'));
        System.err.println(obj.isPalindrome('a'));
    }
}
