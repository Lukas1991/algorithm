package string;

import java.util.ArrayList;
import java.util.List;

/**
 * 722. Remove Comments
 */
public class RemoveComments {

    /**
     * We need to parse the source line by line. Our state is that we either are in a block comment or not.
     *
     * If we start a block comment and we aren't in a block, then we will skip over the next two characters and change our state to be in a block.
     *
     * If we end a block comment and we are in a block, then we will skip over the next two characters and change our state to be not in a block.
     *
     * If we start a line comment and we aren't in a block, then we will ignore the rest of the line.
     *
     * If we aren't in a block comment (and it wasn't the start of a comment), we will record the character we are at.
     *
     * At the end of each line, if we aren't in a block, we will record the line.
     *
     * Time Complexity: O(S)O(S), where SS is the total length of the source code.
     * Space Complexity: O(S)O(S), the space used by recording the source code into ans.
     */
    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        boolean inBlock = false;
        StringBuilder sb = new StringBuilder();

        for (String s : source) {
            int i = 0;
            if (!inBlock) {
                sb = new StringBuilder();
            }
            char[] arr = s.toCharArray();
            while (i < s.length()) {

                if (!inBlock && i+1 < s.length() && arr[i] == '/' && arr[i+1] == '*') {
                    inBlock = true;
                    i++;
                } else if (inBlock && i+1 < s.length() && arr[i] == '*' && arr[i+1] == '/') {
                    inBlock = false;
                    i++;
                } else if (!inBlock && i+1 < s.length() && arr[i] == '/' && arr[i+1] == '/') {
                    //ignore the rest of line
                    break;
                } else if (inBlock) {
                    //nothing
                } else {
                    sb.append(arr[i]);
                }

                i++;
            }

            if (!inBlock && sb.length() > 0) {
                res.add(sb.toString());
            }
        }

        return res;
    }
}
