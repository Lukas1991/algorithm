package Interview.dropbox;

import java.util.BitSet;

/**
 * If maxNumber is 5, need 9 nodes in bitset, indexes are 0-8
 * <p>
 * 0
 * /   \
 * 1     2
 * / \   / \
 * 3  4  5   6
 * / \
 * 7  8
 * <p>
 * Leaf Nodes 7,8,4,5,6 store the boolean value for number(0,1,2,3,4)
 * <p>
 * Time: get, check, releas are logN
 * Space: 2N-1
 */
public class PhoneDirectory3BitSetSegmentTree {

    private BitSet bitset;
    private int maxNumber;

    /**
     * Initialize your data structure here
     *
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */

    public PhoneDirectory3BitSetSegmentTree(int maxNumbers) {
        this.maxNumber = maxNumbers;
        this.bitset = new BitSet(maxNumbers * 2 - 1);
    }

    /**
     * Provide a number which is not assigned to anyone.
     *
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (bitset.get(0)) { //all are used
            return -1;
        }

        int left = 0, right = maxNumber - 1;
        int curNode = 0;

        while (left < right) {
            int mid = (left + right) / 2;

            int leftChild = curNode * 2 + 1;

            if (!bitset.get(leftChild)) {
                curNode = leftChild; //left child
                right = mid;
            } else {
                curNode = curNode * 2 + 2; //right child
                left = mid + 1;
            }
        }

        flip(left);
        return left;
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        if (number < 0 || number >= maxNumber) return false;

        return getStatus(number, 0, 0, maxNumber - 1);
    }

    private boolean getStatus(int number, int curNode, int left, int right) {
        if (bitset.get(curNode)) {
            return false;  //fast return !!!
        }

        //current is the leaf
        if (left == right) return !bitset.get(curNode);

        int mid = (left + right) / 2;
        if (number <= mid) {
            return getStatus(number, curNode * 2 + 1, left, mid);
        } else {
            return getStatus(number, curNode * 2 + 2, mid + 1, right);
        }
    }

    private void flip(int number) {
        setStatus(number, 0, 0, maxNumber - 1);
    }

    private void setStatus(int number, int curNode, int left, int right) {
        if (left == right) {
            bitset.flip(curNode);
            return;
        }

        int mid = (left + right) / 2;
        if (number <= mid) {
            setStatus(number, curNode * 2 + 1, left, mid);
        } else {
            setStatus(number, curNode * 2 + 2, mid + 1, right);
        }

        if (bitset.get(curNode * 2 + 1) && bitset.get(curNode * 2 + 2)) { //both children are used
            bitset.set(curNode); //set to true -> used
        } else {
            bitset.clear(curNode);
        }
    }


    /**
     * Recycle or release a number.
     */
    public void release(int number) {
        if (number < 0 || number >= maxNumber) return;

        if (!check(number)) {
            flip(number);
        }
    }

    public static void main(String[] args) {
        //test();
        //test1();
        test2();
    }

    static void test() {
        PhoneDirectory3BitSetSegmentTree obj = new PhoneDirectory3BitSetSegmentTree(3);

        System.err.println(obj.get());  //0

        System.err.println(obj.get());  //1

        System.err.println(obj.check(2));  //true
        System.err.println(obj.get()); //2
        System.err.println(obj.check(2));  //false
        obj.release(2);
        System.err.println(obj.check(2));  //true
    }

    static void test1() {
        PhoneDirectory3BitSetSegmentTree obj = new PhoneDirectory3BitSetSegmentTree(1);
        System.err.println(obj.get());  //0
        System.err.println(obj.get());  //-1
    }

    static void test2() {
        PhoneDirectory3BitSetSegmentTree obj = new PhoneDirectory3BitSetSegmentTree(5);
        System.err.println(obj.get());  //0
        System.err.println(obj.get());  //1
        System.err.println(obj.get()); //2
        System.err.println(obj.get()); //3
        System.err.println(obj.get()); //4
        System.err.println(obj.get()); //-1
    }
}
