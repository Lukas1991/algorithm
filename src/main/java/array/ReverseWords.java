package array;

public class ReverseWords {

    /**
     * 186. Reverse Words in a String II,
     * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
     *
     * For example,
     * Given s = "the sky is blue",
     * return "blue is sky the".
     *
     * Could you do it in-place without allocating extra space?
     */
    public String reverseWords2(char[] str) {
        //1. reverse entire string
        reverse(str, 0, str.length - 1);

        //2. reverse each word
        int start = 0;
        for (int end = 0; end < str.length; end++) {
            if (str[end] == ' ') {
                reverse(str, start, end - 1);
                start = end + 1;
            }
        }

        //3. reverse last word
        reverse(str, start, str.length - 1);

        return new String(str);
    }

    private void reverse(char[] str, int i, int j) {
        while (i < j) {
            char c = str[i];
            str[i] = str[j];
            str[j] = c;
            i++;
            j--;
        }
    }


    /**
     * 151. Reverse Words in a String
     * input string may contain leading or trailing spaces, and there can be multiple spaces between two words
     * For example,
     * Given s = "  the  sky   is blue  ",
     * return "blue is sky the".
     */
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        //1. reverse entire string
        reverse(arr, 0, arr.length - 1);

        //2. reverse each word
        int start = 0;
        while (start < arr.length) {
            if (arr[start] == ' ') {
                start++;
            } else {
                int end = start;
                while (end < arr.length && arr[end] != ' ') {
                    end++;
                }

                reverse(arr, start, end - 1);
                start = end + 1;
            }
        }

        //3. clean spaces
        int preEnd = 0;
        int i = 0;
        while (i < arr.length) {
            if (arr[i] == ' ') {
                i++;
            } else {
                //append 1 space between words
                if (preEnd > 0) {
                    arr[preEnd] = ' ';
                    preEnd++;
                }

                while (i < arr.length && arr[i] != ' ') {
                    arr[preEnd] = arr[i];
                    preEnd++;
                    i++;
                }
            }
        }

        //length = preEnd
        return new String(arr, 0, preEnd);
    }

    public static void main(String[] args) {
        String test = "the sky is blue";
        ReverseWords obj = new ReverseWords();
        //System.err.println(obj.reverseWords2(test.toCharArray()));

        //System.err.println(obj.reverseWords("  the  sky   is blue  "));
        System.err.println(obj.reverseWords("a "));

    }

}