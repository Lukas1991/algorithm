package Interview.amzn.oa;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {

        int[] last = new int[26];
        for (int i = 0; i < S.length(); i++) {
            last[S.charAt(i) - 'a'] = i;
        }

        List<Integer> ret = new ArrayList<>();
        int left = 0;
        int right = 0;
        for (int i = 0; i < S.length(); i++) {

            right = Math.max(right, last[S.charAt(i) - 'a']);
            if (right == i) {

                ret.add(right - left + 1);
                left = right + 1;
            }
        }

        return ret;
    }

    public int maxPartitionLabel(String S) {

        int[] last = new int[26];
        for (int i = 0; i < S.length(); i++) {
            last[S.charAt(i) - 'a'] = i;
        }

        int maxLength = 0;

        int left = 0;
        int right = 0;

        for (int i = 0; i < S.length(); i++) {
            right = Math.max(right, last[S.charAt(i) - 'a']);
            if (right == i) {
                maxLength = Math.max(maxLength, right - left + 1);
                left = right + 1;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        PartitionLabels obj = new PartitionLabels();
        String s = "acafghbeb";  //"abcab";
        List<Integer> res = obj.partitionLabels(s);
        System.err.println(res.toString());
        System.err.println(obj.maxPartitionLabel(s));
    }
}
