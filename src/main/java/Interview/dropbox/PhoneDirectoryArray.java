package Interview.dropbox;

public class PhoneDirectoryArray {

    int max;
    boolean[] array;

    public PhoneDirectoryArray(int maxNumbers) {
        array = new boolean[getPowOf2(maxNumbers) * 2 - 1];
        max = maxNumbers;
        buildTree(0, 0, getPowOf2(maxNumbers));
    }

    int getPowOf2(int n) {
        int base = 0;
        int cur = 1;
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
        int num = findAvai(0, 0, max);
        if (num == -1) {
            return -1;
        } else {
            modify(0, 0, max, num, false);
            return num;
        }
    }

    int findAvai(int root, int start, int end) {
        if (!array[root]) {
            return -1;
        }

        if (start == end && array[root]) {
            return start;
        }

        int left = 2 * root + 1;
        int right = 2 * root + 2;
        int mid = (start + end) / 2;

        if (array[left]) {
            return findAvai(left, start, mid);
        } else {
            return findAvai(right, mid + 1, end);
        }

    }

    void modify(int node, int start, int end, int num, boolean value) {
        if (start == num && end == num) {
            array[node] = value;
            return;
        }

        int left = 2 * node + 1;
        int right = 2 * node + 2;
        int mid = (start + end) / 2;

        if (num <= mid) {
            modify(left, start, mid, num, value);
        } else {
            modify(right, mid + 1, end, num, value);
        }

        array[node] = array[left] || array[right];
    }

    public boolean check(int number) {
        if (number < 0 || number >= max) {
            return false;
        }

        return query(0, number, number);
    }

    public void release(int number) {
        if (number < 0 || number >= max) {
            return;
        }
        modify(0, 0, max, number, true);
    }

    boolean query(int root, int start, int end) {
        if (!array[root]) {
            return false;
        }

        if (start == end) {
            return array[root];
        }

        int mid = (start + end) / 2;

        int left = 2 * root + 1;
        int right = 2 * root + 2;

        boolean hasAvai = false;
        if (mid >= start) {
            hasAvai |= query(left, start, end);
        }

        if (hasAvai) {
            return true;
        }

        if (mid < end) {
            hasAvai |= query(right, start, end);
        }

        return hasAvai;
    }

    void buildTree(int root, int start, int end) {

        if (start > end || root >= array.length) {
            //array[root] = false;
            return;
        }

        if (start == end) {
            array[root] = start > max ? false : true;
            return;
        }

        int left = 2 * root + 1;
        int right = 2 * root + 2;
        int mid = (start + end) / 2;

        buildTree(left, start, mid);
        buildTree(right, mid + 1, end);

        if (left < array.length) {
            array[root] |= array[left];
        }

        if (right < array.length) {
            array[root] |= array[right];
        }
    }

    public static void main(String[] args) {
        PhoneDirectoryArray obj = new PhoneDirectoryArray(3);
        System.err.println(obj.get());  //0

        System.err.println(obj.get());  //1

        System.err.println(obj.check(2));  //true
        System.err.println(obj.get()); //2
        System.err.println(obj.check(2));  //false
        obj.release(2);
        System.err.println(obj.check(2));  //true

    }
}
