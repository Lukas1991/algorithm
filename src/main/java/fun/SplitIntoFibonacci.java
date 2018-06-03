package fun;

import java.util.ArrayList;
import java.util.List;

/**
 * Split Array into Fibonacci Sequence
 * <p>
 * Input: "123456579"
 * Output: [123,456,579]
 */
public class SplitIntoFibonacci {

    public List<Integer> splitIntoFibonacci(String S) {
        int n = S.length();
        List<Integer> res = new ArrayList<>();
        int first = 0;
        int second = 0;

        for (int i = 1; i <= n - 2; i++) {
            first = toInt(S.substring(0, i));
            if (first == -1) continue;

            for (int j = i; j <= n - 1; j++) {
                second = toInt(S.substring(i, j));
                if (second == -1) continue;

                List<Integer> tmp = spilt(S, j, first, second);
                if (tmp != null) {
                    return tmp;
                }

            }
        }

        return res;
    }

    List<Integer> spilt(String S, int start, int first, int second) {
        List<Integer> tmp = new ArrayList<>();
        tmp.add(first);
        tmp.add(second);

        int i = start + 1;
        boolean can = false;
        while (i <= S.length()) {
            int sum = first + second;

            int cur = toInt(S.substring(start, i));
            if (cur == -1) {
                break;
            } else if (cur == sum) {
                first = second;
                second = cur;
                start = i;

                tmp.add(cur);
                if (i == S.length()) {
                    can = true;
                }
                i++;
            } else {
                i++;
            }
        }

        if (can) {
            return tmp;
        } else {
            return null;
        }

    }

    int toInt(String s) {
        if (s.startsWith("0") && s.length() > 1) {
            return -1;
        }
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
