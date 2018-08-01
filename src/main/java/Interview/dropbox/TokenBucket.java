package Interview.dropbox;

import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucket {

    int maxCapacity;
    int fillRate; //fill number per second
    AtomicInteger count;
    volatile int lastTime;
    static Object LOCK = new Object();

    public TokenBucket(int maxCapacity, int fillRate) {
        this.maxCapacity = maxCapacity;
        this.fillRate = fillRate;
        count = new AtomicInteger(maxCapacity);
    }

    public boolean getTokens(int n) throws InterruptedException {
        fill();

        if (count.get() > n) {
            synchronized (LOCK) {
                count.set(count.get() - n);
            }
            return true;
        }

        LOCK.wait(1000 * (n - count.get()));

        return getTokens(n);
    }

    void fill() {
        int currentTime = 1; //convert from current time
        int diff = currentTime - lastTime;
        lastTime = currentTime;
        synchronized (LOCK) {
            count.set(count.get() + diff * fillRate);
        }
    }

    public void put(int n) {
        if (count.get() + n > maxCapacity) {
            return;
        }

        count.set(count.get() + n);
        LOCK.notify();
    }
}
