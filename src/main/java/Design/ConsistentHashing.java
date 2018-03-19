package Design;

import java.util.*;

public class ConsistentHashing {

    public int n, k;
    //key is index, value is machine_id
    public TreeMap<Integer, Integer> indexMap = null;


    public ConsistentHashing(int n, int k) {
        this.n = n;
        this.k = k;
        this.indexMap = new TreeMap<>();
    }

    /*
     * add a new machine, return a list of shard ids.
     */
    public List<Integer> addMachine(int machine_id) {
        Random random = new Random();
        List<Integer> random_nums = new ArrayList<>();

        for (int i = 0; i < k; ++i) {
            int index = random.nextInt(n);
            while (indexMap.containsKey(index)) {
                index = random.nextInt(n);
            }

            indexMap.put(index, machine_id);
            random_nums.add(index);
        }

        Collections.sort(random_nums);
        return random_nums;
    }

    /*
     * return machine id
     */
    public int getMachineIdByHashCode(int hashcode) {
        Integer machineIndex = indexMap.ceilingKey(hashcode);
        if (machineIndex == null) {
            machineIndex = indexMap.firstKey();
        }

        return indexMap.get(machineIndex);
    }
}
