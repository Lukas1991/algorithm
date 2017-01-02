package BitManipulation;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {

    /**
     * The idea is to generate the sequence iteratively. For example, when n=3, we can get the result based on n=2.
     * 00,01,11,10 -> (000,001,011,010 ) (110,111,101,100).
     * The middle two numbers only differ at their highest bit, while the rest numbers of part two are exactly symmetric of part one.
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i=0; i<n; i++) {
            int size = res.size();

            //reverse order
            for (int j=size-1; j>=0; j--) {
                int leftBit = 1<<i;
                res.add(res.get(j) | leftBit);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        GrayCode grayCode = new GrayCode();
        grayCode.grayCode(3);
    }
}
