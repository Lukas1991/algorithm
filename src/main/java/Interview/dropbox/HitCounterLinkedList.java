package Interview.dropbox;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class HitCounterLinkedList {

    private final Object LOCK = new Object();
    LinkedList<AtomicInteger> queue;
    AtomicInteger sum = new AtomicInteger();

    private volatile int tailSecond = 0;
    private static int N;

    //AtomicInteger count = new AtomicInteger();
    //private volatile int curSecond = 0;


    public HitCounterLinkedList() {
        N = 300;
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            queue.add(new AtomicInteger(0));
        }
    }

    public void hit(int timestamp) {

        if (tailSecond != timestamp) {
            synchronized (LOCK) {
                move(timestamp);
            }
        }

        queue.getLast().getAndIncrement();
        sum.getAndIncrement();
    }

    void move(int timestamp) {
        if (tailSecond == timestamp) {
            return;
        }

        int diff = (timestamp - tailSecond);
        if (diff >= N) {
            for (int i = 0; i < N; i++) {
                queue.get(i).set(0);
            }
            sum.set(0);
        } else {
            int step = diff % N;
            for (int i = 0; i < step; i++) {
                queue.addLast(new AtomicInteger(0));
                int toDelete = queue.removeFirst().get();
                sum.addAndGet(toDelete * -1);
            }
        }

        tailSecond = timestamp;
    }

    public int getHits(int timestamp) {
        if (tailSecond != timestamp) {
            synchronized (LOCK) {
                move(timestamp);
            }
        }

        return sum.get();
    }

    public static void main(String[] args) {
        HitCounterLinkedList obj = new HitCounterLinkedList();
        obj.hit(625);
        System.err.println(obj.getHits(684)); //1
        System.err.println(obj.getHits(1006)); //0
    }
}
