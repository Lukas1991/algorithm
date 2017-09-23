package fun;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NextTime {

    public String nextTime(String time) {
        String[] times = time.split(":");
        int hour = Integer.valueOf(times[0]);
        int min = Integer.valueOf(times[1]);

        Set<Integer> set = new HashSet<>();

        for (char c : time.toCharArray()) {
            if (c != ':') {
                set.add(c - '0');
            }
        }

        List<Integer> minues = getValid(set, 59);
        List<Integer> hours = getValid(set, 23);

        int nextMin = getNext(minues, min);
        if (nextMin < min) {
            int nextHour = getNext(hours, hour);
            return toTime(nextHour, nextMin);
        } else {
            return toTime(hour, nextMin);
        }
    }

    private List<Integer> getValid(Set<Integer> set, int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            if (set.contains(i / 10) && set.contains(i % 10)) {
                list.add(i);
            }
        }
        //System.err.println(list.toString());
        return list;
    }

    private int getNext(List<Integer> list, int a) {
        int index = list.indexOf(a);
        if (index + 1 < list.size()) {
            return list.get(index + 1);
        } else {
            return list.get(0);
        }
    }

    private String toTime(int h, int m) {
        String s = "";
        if (h < 10) {
            s += "0" + h;
        } else {
            s += h;
        }

        s += ":";

        if (m < 10) {
            s += "0" + m;
        } else {
            s += m;
        }
        return s;
    }

    public static void main(String[] args) {
        NextTime obj = new NextTime();
        System.err.println(obj.nextTime("01:23")); //01:30
        System.err.println(obj.nextTime("02:02")); //02:20
        System.err.println(obj.nextTime("23:59")); //22:22
        System.err.println(obj.nextTime("11:11")); //11:11

    }

}
