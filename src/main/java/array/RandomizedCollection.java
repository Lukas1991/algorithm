package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed
 * The probability of each element being returned is linearly related to the number of same value the collection contains.
 * if insert [1,1,2], 1 with the probability 2/3, and 2 with the probability 1/3.
 *
 * Use LinkedHashSet instead of Hashset, because set.iterator() might be costly when the set is large. Using LinkedHashSet can be considered as O(1) if we only get the first element to remove.
 */
public class RandomizedCollection {

    ArrayList<Integer> list = new ArrayList<>();

    //key is number, value is set of locations in list
    Map<Integer, Set<Integer>> map = new HashMap<>();

    Random random = new Random();

    /** Initialize your data structure here. */
    public RandomizedCollection() {
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    //add to end of list, add the position to map
    public boolean insert(int val) {
        boolean contain = map.containsKey(val);

        if (!contain) {
            map.put(val, new HashSet<>());
        }

        Set<Integer> locs = map.get(val);

        list.add(val);
        locs.add(list.size() - 1);

        return !contain;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    /**
     * choose one (any) position from the set in the map, then move the end of list to the specific position, and then remove the last of the list. Also update the map information. 
     * 3.1. Why LinkedHashSet? Because it's the best data structure implementing set and allowing us to retrieve one element in O(1).
     * 3.2. Why do crazy stuffs in list? Because we want to make sure that the list is consecutive so that it's easier for us to get the random element.
     */
    public boolean remove(int val) {
        if (map.containsKey(val)) {

            Set<Integer> locs = map.get(val);
            int valLoc = locs.iterator().next();
            //valLoc is at end
            locs.remove(valLoc);

            int endIndex = list.size() - 1;

            if (valLoc < endIndex) {
                int endValue = list.get(endIndex);
                list.set(valLoc, endValue);

                map.get(endValue).remove(endIndex);//O(1)
                map.get(endValue).add(valLoc); //O(1)
            }

            list.remove(endIndex);

            if (locs.isEmpty()) {
                map.remove(val);
            }

            return true;
        } else {
            return false;
        }
    }

    /** Get a random element from the collection. */
    //O(1)
    public int getRandom() {
        int r = random.nextInt(list.size());
        return list.get(r);
    }

    public static void main(String[] args) {
        RandomizedCollection obj = new RandomizedCollection();
        obj.insert(0);
        obj.insert(1);
        obj.insert(2);
        obj.insert(3);
        obj.insert(3);

        obj.remove(2);
        obj.remove(3);
        obj.remove(0);
    }
}
