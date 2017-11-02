package BitManipulation;

import java.util.ArrayList;
import java.util.List;

public class BinaryWatch {

    public static void main(String[] args) {
        BinaryWatch obj = new BinaryWatch();
        System.err.println(obj.readBinaryWatch(0).toString());
    }

    public List<String> readBinaryWatch(int num) {
        List<String> times = new ArrayList<>();

        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h * 64 + m) == num) {
                    times.add(String.format("%d:%02d", h, m));
                }
            }
        }

        return times;
    }

}
