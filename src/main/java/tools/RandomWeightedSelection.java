package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomWeightedSelection {

    public int getRandom(List<Integer> list) {

        int sum = 0;
        for (int a : list) {
            sum += a;
        }

        int r = new Random().nextInt(sum);

        int count = 0;
        for (int a : list) {
            count += a;

            if (count > r) {
                return a;
            }
        }
        throw new RuntimeException("should never come to here");
    }

    public static void main(String[] arg) {
        RandomWeightedSelection obj = new RandomWeightedSelection();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(2);

        Map<Integer, Integer> map = new HashMap<>();

        int total = 1000000;
        while (total > 0) {
            int a = obj.getRandom(list);
            map.put(a, map.getOrDefault(a, 0) + 1);
            total--;
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            System.err.println(entry.getKey() + ", " + entry.getValue() /1000000.0);
        }

        for (int a : list) {
            System.err.println(a/7.0);
        }
    }
}
