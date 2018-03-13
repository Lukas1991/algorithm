package Interview.dropbox;

public class HitCounter2 {

    int N;
    int[] times;
    int[] hits;

    public HitCounter2() {
        N = 300;
        times = new int[N];
        hits = new int[N];
    }

    public void hit(int timestamp) {
        int index = timestamp % N;
        if (times[index] == timestamp) {
            hits[index]++;
        } else {
            times[index] = timestamp;
            hits[index] = 1;
        }
    }

    public int getHits(int timestamp) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (timestamp - times[i] < N) {
                sum += hits[i];
            }
        }

        return sum;
    }

    //--------------------- Improved ---------------------------------------------------------------
    //int N
    Hit[] hitArray;

    public HitCounter2(boolean improved) {
        N = 300;
        hitArray = new Hit[N];
        for (int i = 0; i < N; i++) {
            hitArray[i] = new Hit(i, 0);
        }
    }

    public void hit2(int timestamp) {
        int index = timestamp % N;
        Hit hit = hitArray[index];

        if (hit.time == timestamp) {
            hit.hit++;
        } else {
            hit.time = timestamp;
            hit.hit = 1;
        }
    }

    public int getHits2(int timestamp) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            Hit hit = hitArray[i];
            if (timestamp - hit.time < N) {
                sum += hit.hit;
            }
        }

        return sum;
    }

    class Hit {
        int time;
        int hit;

        public Hit(int time, int hit) {
            this.time = time;
            this.hit = hit;
        }
    }
}
