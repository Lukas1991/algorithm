package Interview.twosigma;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ModuleIterator implements Iterator<Integer> {
    Iterator<Integer> innerIterator;
    int curValue;

    public ModuleIterator(Iterator<Integer> iterator) {
        innerIterator = iterator;
        curValue = 1; //default value
    }

    @Override
    public boolean hasNext() {

        while (curValue % 5 != 0 && innerIterator.hasNext()) {
            Integer tmp = innerIterator.next(); //use Integer

            if (tmp != null) {
                curValue = tmp;
            }
        }

        return curValue % 5 == 0;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            int result = curValue;

            curValue = 1;

            return result;
        } else {
            throw new NoSuchElementException();
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

//        Iterator it = list.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }

        ModuleIterator moduleIterator = new ModuleIterator(list.iterator());
        while (moduleIterator.hasNext()) {
            System.out.println(moduleIterator.next());
        }

        testEmptyList();
    }

    //@Test
    public static void testEmptyList() {
        List<Integer> list = new ArrayList<>();

        ModuleIterator moduleIterator = new ModuleIterator(list.iterator());
        while (moduleIterator.hasNext()) {
            System.out.println(moduleIterator.next());
        }

    }

}
