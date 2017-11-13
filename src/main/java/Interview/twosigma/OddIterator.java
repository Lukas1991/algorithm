package Interview.twosigma;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class OddIterator {

    private Integer next; //either list is empty, or the value is retrived before
    private Iterator<Integer> it;

    public OddIterator(Iterator<Integer> it) {
        this.it = it;
    }

    public boolean hasNext() {
        //step - 1
        if (next != null) {
            return true;
        }

        //step - 2
        while (it.hasNext()) {
            Integer temp = it.next();
            if (temp != null && temp % 5 == 0) {
                next = temp;
                return true;
            }
        }
        //step - 3
        return false;
    }

    public int next() {
        if (hasNext()) {
            Integer res = next;
            next = null;
            return res;
        } else {
            throw new NoSuchElementException("no such element exception");
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(5);  //duplicate
        list.add(null);  // null
        list.add(6);
        list.add(10);
        list.add(11);

        System.out.println(list.toString());

        OddIterator oddIterator = new OddIterator(list.iterator());
        while (oddIterator.hasNext()) {
            System.out.println(oddIterator.next());
        }

        testEmptyList();
    }

    //@Test
    public static void testEmptyList() {
        List<Integer> list = new ArrayList<>();

        OddIterator oddIterator = new OddIterator(list.iterator());
        while (oddIterator.hasNext()) {
            System.out.println(oddIterator.next());
        }
    }

}
