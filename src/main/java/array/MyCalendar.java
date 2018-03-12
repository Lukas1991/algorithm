package array;

import java.util.TreeMap;

public class MyCalendar {
    //key is start, value is end. sort by start
    TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new TreeMap<>();
    }


    public boolean book1(int start, int end) {
        Integer prev = calendar.floorKey(start); //smaller than or equal to key, or null if there is no such key.
        Integer next = calendar.ceilingKey(start); //greater than or equal to key, or null if there is no such key.

        //preEnd <= start <= end <= next start
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }

    public boolean book(int start, int end) {
        if (calendar.containsKey(start)) {
            return false;
        }

        //不包括重复
        Integer prev = calendar.lowerKey(start); //比start小的最大的key, or null if there is no such key.
        Integer next = calendar.higherKey(start); //比start大的最小的key, or null if there is no such key.

        //preEnd <= start <= end <= next start
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}
