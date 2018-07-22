package fun;

import java.util.Iterator;
import java.util.TreeSet;

public class ExamRoom {

    boolean first = false;
    boolean last = false;
    int N;
    TreeSet<Integer> treeSet = new TreeSet<>();

    public ExamRoom(int N) {
        this.N = N;
    }

    public int seat() {
        if (treeSet.isEmpty()) {
            treeSet.add(0);
            first = true;
            return 0;
        }
        int maxDis = 0;
        int index = 0;
        int preIndex = -1;

        Iterator iterator = treeSet.iterator();

        while (iterator.hasNext()) {
            int i = (int) iterator.next();
            if (preIndex == -1) {
                preIndex = i;
                if (!first) {
                    maxDis = i;
                    index = 0;
                }
            } else {
                int mid = (i + preIndex) / 2;
                int dis = mid - preIndex;
                if (dis > maxDis) {
                    maxDis = dis;
                    index = mid;
                }
                preIndex = i;
            }

        }

        if (last == false && preIndex != -1) {
            int dis = N - 1 - preIndex;
            if (dis > maxDis) {
                index = N - 1;
                last = true;
            }
        }

        treeSet.add(index);
        return index;
    }

    public void leave(int p) {
        if (p == 0) {
            first = false;
        } else if (p == N - 1) {
            last = false;
        }
        treeSet.remove(p);
    }

    public static void main(String[] args) {
        ExamRoom obj = new ExamRoom(10);
        System.err.println(obj.seat());
        System.err.println(obj.seat());
        System.err.println(obj.seat());

        obj.leave(0);
        obj.leave(4);
        System.err.println(obj.seat());
        System.err.println(obj.seat());
        System.err.println(obj.seat());
        System.err.println(obj.seat());
        System.err.println(obj.seat());
        System.err.println(obj.seat());
        System.err.println(obj.seat());
        System.err.println(obj.seat());
        System.err.println(obj.seat());

        obj.leave(0);
        obj.leave(4);

        System.err.println(obj.seat());
        System.err.println(obj.seat());
    }
}
