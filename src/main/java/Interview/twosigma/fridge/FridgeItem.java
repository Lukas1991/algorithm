package Interview.twosigma.fridge;

import java.util.concurrent.Semaphore;

public class FridgeItem {
    int maxCount;
    int currentCount;
    Semaphore semaphore;

    public FridgeItem(int maxCount) {
        this.maxCount = maxCount;
        this.currentCount = maxCount;
        this.semaphore = new Semaphore(maxCount);
    }

    public boolean eat() {
        synchronized (this) {
            if (currentCount > 0) {
                currentCount--;
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean restorck() {
        synchronized (this) {
            if (currentCount < maxCount) {
                currentCount++;
                return true;
            } else {
                return false;
            }
        }
    }

    public String toString() {
        return "current is: " + currentCount + ", max is " + maxCount;
    }
}
