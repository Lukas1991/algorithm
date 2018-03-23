package Interview.dropbox;

public class PhoneDirectoryArray {

    int max;
    int firstLeafIndex;
    boolean[] array;

    //O(n) to build tree
    public PhoneDirectoryArray(int maxNumbers) {
        max = maxNumbers;

        //转换成平衡二叉树，比maxNumbers大的2的N次方
        int balancedLeafs = getPowOf2(maxNumbers);
        array = new boolean[balancedLeafs * 2 - 1];
        firstLeafIndex = balancedLeafs - 1;
        buildTree(0);
    }

    void buildTree(int index) {
        if (index >= firstLeafIndex) {
            //is leaf
            if (index < firstLeafIndex + max) {
                array[index] = true;
            } else {
                array[index] = false;
            }
        } else {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            buildTree(left);
            buildTree(right);
            array[index] = array[left] || array[right];
        }
    }

    int getPowOf2(int n) {
        int base = 1;
        int cur = 2;
        while (cur < n) {
            base++;
            cur *= 2;
        }
        return cur;
    }

    /**
     * Provide a number which is not assigned to anyone.
     *
     * @return - Return an available number. Return -1 if none is available.
     * log(N)
     */
    public int get() {
        if (!array[0]) {
            return -1;
        }

        int index = findAvaiIndex(0);
        array[index] = false;

        int parent = (index - 1) / 2;
        modify(parent);
        //return the number
        return index - firstLeafIndex;
    }

    int findAvaiIndex(int index) {
        while (index < firstLeafIndex) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (array[left]) {
                index = left;
            } else {
                index = right;
            }
        }

        return index;
    }

    //modify的都non-leaf
    void modify(int index) {
        while (index >= 0) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left < array.length && right < array.length) {
                array[index] = array[left] || array[right];
            } else {
                array[index] = array[left];
            }
            //不可能没有左，但是有右

            //防止死循环
            if (index == 0) {
                break;
            }
            index = (index - 1) / 2;
        }
    }

    //O(1)
    public boolean check(int number) {
        if (number < 0 || number >= max) {
            return false;
        }
        int index = firstLeafIndex + number;
        return array[index];
    }

    //O(logn)
    public void release(int number) {
        if (number < 0 || number >= max) {
            return;
        }
        int index = firstLeafIndex + number;
        array[index] = true;
        int parent = (index - 1) / 2;
        modify(parent);
    }


    public static void main(String[] args) {
        test();
        test1();
        test2();
    }

    static void test() {
        PhoneDirectoryArray obj = new PhoneDirectoryArray(3);

        System.err.println(obj.get());  //0

        System.err.println(obj.get());  //1

        System.err.println(obj.check(2));  //true
        System.err.println(obj.get()); //2
        System.err.println(obj.check(2));  //false
        obj.release(2);
        System.err.println(obj.check(2));  //true
    }

    static void test1() {
        PhoneDirectoryArray obj = new PhoneDirectoryArray(1);
        System.err.println(obj.get());  //0
        System.err.println(obj.get());  //-1
    }

    static void test2() {
        PhoneDirectoryArray obj = new PhoneDirectoryArray(5);
        System.err.println(obj.get());  //0
        System.err.println(obj.get());  //1
        System.err.println(obj.get()); //2
        System.err.println(obj.get()); //3
        System.err.println(obj.get()); //4
        System.err.println(obj.get()); //-1
    }
}
