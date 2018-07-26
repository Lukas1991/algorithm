package Interview.dropbox;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 此方法仅限 timestamp 递增（可以相等，一个时间多个Hit）
 * you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing).
 * You may assume that the earliest timestamp starts at 1
 * It is possible that several hits arrive roughly at the same time.  此方法支持
 */
public class HitCounterCircularArray {
    private static final Object LOCK = new Object();
    int N;
    AtomicInteger[] counts;
    AtomicInteger sum = new AtomicInteger(0);
    private volatile int lastTime;

    public HitCounterCircularArray() {
        N = 300;
        counts = new AtomicInteger[N];
        lastTime = 0;
        for (int i = 0; i < N; i++) {
            counts[i] = new AtomicInteger(0);
        }
    }

    public void hit(int timestamp) {

        if (lastTime != timestamp) {
            synchronized (LOCK) {
                moveIndex(timestamp);
            }
        }

        counts[timestamp % N].incrementAndGet();
        sum.incrementAndGet();
    }

    public int getHits(int timestamp) {
        if (lastTime != timestamp) {
            synchronized (LOCK) {
                moveIndex(timestamp);
            }
        }

        return sum.get();
    }

    //clear the cells between current timestamp and lastTime. update sum. move index to current timestamp's cell
    //[...|last|XXXXX|current| ...] the cells between are stale because they are not updated.
    void moveIndex(int timestamp) {
        int gap = Math.min(timestamp - lastTime, N);

        int index = lastTime % N;

        for (int i = 0; i < gap; i++) {
            index = (index + 1) % N; //move index 1 step
            sum.addAndGet(counts[index].get() * -1);
            counts[index].set(0); //clear cell
        }

        lastTime = timestamp;
    }

    public static void main(String[] args) {
        HitCounterCircularArray obj = new HitCounterCircularArray();
        obj.hit(1);
        obj.hit(2);
        obj.hit(3);
        obj.hit(3);
        System.err.println(obj.getHits(3)); //4
        System.err.println(obj.getHits(4)); //4

        obj.hit(300);

        System.err.println(obj.getHits(300)); //5
        System.err.println(obj.getHits(301)); //4
    }
}
