package Interview.dropbox;

import java.util.BitSet;

// Bitset: O(n) time, O(1) space
public class PhoneDirectoryBitSet {
    BitSet bitSet;
    int max = 0;
    int index = 0;

    /**
     * Initialize your data structure here
     *
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    public PhoneDirectoryBitSet(int maxNumbers) {
        bitSet = new BitSet(maxNumbers);
        max = maxNumbers;
    }

    /**
     * Provide a number which is not assigned to anyone.
     *
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (index == max) {
            return -1;
        } else {
            int num = index;
            bitSet.set(index);
            index = bitSet.nextClearBit(index); //takes O(n)
            return num;
        }
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        if (number < 0 || number >= max) {
            return false;
        }

        return !bitSet.get(number);
    }

    /**
     * Recycle or release a number.
     */
    //number 超出范围时，bitset.get return false
    public void release(int number) {
        if (bitSet.get(number)) {
            bitSet.clear(number);
            index = Math.min(index, number);
        }
    }
}
