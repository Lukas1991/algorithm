package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 380. Insert Delete GetRandom O(1)
 * Each element must have the same probability of being returned.
 * all insert, remove, getRandom operations in average O(1) time.
 */
public class RandomizedSet {

    ArrayList<Integer> list = new ArrayList<>();

    //key is number, value is it's location in list
    Map<Integer, Integer> map = new HashMap<>();

    Random random = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    //O(1)
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            //add to last
            int endIndex = list.size();
            list.add(val);
            map.put(val, endIndex);

            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    //O(1)
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int index = map.get(val);

            //move this value to end of list
            if (index < list.size() - 1) {
                int lastValue = list.get(list.size() - 1);
                list.set(index, lastValue);
                map.put(lastValue, index);
            }

            //remove end of the list
            list.remove(list.size() - 1);  //It is O(1) only when removing the last element by index.
            map.remove(val);

            return true;
        } else {
            return false;
        }
    }

    /** Get a random element from the set. */
    //O(1)
    public int getRandom() {
        int r = random.nextInt(list.size());
        return list.get(r);
    }
}
