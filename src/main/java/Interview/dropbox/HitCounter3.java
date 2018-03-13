package Interview.dropbox;

/**
 * 此方法仅限 timestamp 递增（可以相等，一个时间多个Hit）
 * you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing).
 * You may assume that the earliest timestamp starts at 1
 * It is possible that several hits arrive roughly at the same time.  此方法支持
 */
public class HitCounter3 {
    int N;
    int[] counts;
    int sum;

    int lastIndex;
    int lastTime;

    public HitCounter3() {
        N = 300;
        counts = new int[N];
        lastIndex = 0;
        lastTime = 0;
        sum = 0;
    }

    public void hit(int timestamp) {
        moveIndex(timestamp);
        counts[lastIndex]++;
        sum++;
    }

    public int getHits(int timestamp) {
        moveIndex(timestamp);
        return sum;
    }

    //clear the cells between current timestamp and lastTime. update sum. move index to current timestamp's cell
    void moveIndex(int timestamp) {
        int gap = Math.min(timestamp - lastTime, N);

        for (int i = 0; i < gap; i++) {
            lastIndex = (lastIndex + 1) % N; //move index 1 step
            sum -= counts[lastIndex];
            counts[lastIndex] = 0; //clear cell
        }

        lastTime = timestamp;
    }

    public static void main(String[] args) {
        HitCounter3 obj = new HitCounter3();
        obj.hit(1);
        obj.hit(2);
        obj.hit(2);
        obj.hit(3);

        System.err.println(obj.getHits(200)); //4
    }
}
