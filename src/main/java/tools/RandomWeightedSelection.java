package tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomWeightedSelection {

    int sum;

    Map<String, Integer> map = new HashMap<>();

    Random random = new Random();

    void put (String obj, int weight) {
        if (weight == 0) {
            if (map.containsKey(obj)) {
                int oldWeight = map.remove(obj);
                sum -= oldWeight;
            }
        } else {
            if (map.containsKey(obj)) {
                int oldWeight = map.get(obj);
                sum -= oldWeight;
            }
            map.put(obj, weight);
            sum += weight;
        }
    }

    String getRandom() {
        int r = random.nextInt(sum); //random doesn't have a nextLong

        int count = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            count += entry.getValue();

            if (count > r) {
                return entry.getKey();
            }
        }

        throw new RuntimeException("should never come to here");
    }

    //if use lombok, we can @Setter @VisibleForTesting
    public int getSum() {
        return sum;
    }

    //when input is weight list
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
        test();
    }

    public static void test() {
        RandomWeightedSelection obj = new RandomWeightedSelection();
        obj.put("A", 3);
        obj.put("B", 30);
        obj.put("A", 13);
        obj.put("C", 5);
        obj.put("B", 0);
        obj.put("D", 0);

        Map<String, Integer> countMap = new HashMap<>();

        int total = 1000000;
        for (int i = 0; i < total; i++) {
            String s = obj.getRandom();
            countMap.put(s, countMap.getOrDefault(s, 0) + 1);
        }

        //actual probability
        for (Map.Entry<String, Integer> entry: countMap.entrySet()) {
            System.err.println(entry.getKey() + ", " + entry.getValue() * 1.0 / total);
        }

        //expected probability
        for (Map.Entry<String, Integer> entry: obj.map.entrySet()) {
            System.err.println(entry.getKey() + ", " + entry.getValue() * 1.0 / obj.getSum());
        }
    }
}
