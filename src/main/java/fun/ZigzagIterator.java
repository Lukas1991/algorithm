package fun;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZigzagIterator {
    Iterator<Integer> i1;
    Iterator<Integer> i2;

    Iterator<Integer> it;
    boolean flip = true;

    /*
    * @param v1: A 1d vector
    * @param v2: A 1d vector
    */
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i1 = v1.iterator();
        i2 = v2.iterator();
    }

    /*
     * @return: An integer
     */
    public int next() {
        if (hasNext()) {
            flip = !flip;
            return it.next();
        } else {
            return -1;
        }
    }

    /*
     * @return: True if has next
     */
    public boolean hasNext() {
        if (flip) {
            it = i1;
            if (!it.hasNext()) {
                it = i2;
            }
        } else {
            it = i2;
            if (!it.hasNext()) {
                it = i1;
            }
        }
        return it.hasNext();
    }

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(2);

        List<Integer> l2 = new ArrayList<>();
        l2.add(3);
        l2.add(4);
        l2.add(5);
        l2.add(6);

        ZigzagIterator obj = new ZigzagIterator(l1, l2);
        while (obj.hasNext()) {
            System.err.println(obj.next());
        }
    }

}
