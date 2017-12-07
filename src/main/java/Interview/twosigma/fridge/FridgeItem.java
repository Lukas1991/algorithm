package Interview.twosigma.fridge;

public class FridgeItem {
    int maxCount;
    int currentCount;

    public FridgeItem(int maxCount) {
        this.maxCount = maxCount;
        this.currentCount = maxCount;
    }

    public void eat() {
        currentCount--;
    }

    public void restorck() {
        currentCount++;
    }

    public String toString() {
        return "current is: " + currentCount + ", max is " + maxCount;
    }
}
