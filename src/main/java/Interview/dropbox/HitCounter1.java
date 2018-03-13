package Interview.dropbox;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class HitCounter1 {

    int N;
    Map<Integer, Integer> hitMap;
    Queue<Integer> queue;

    public HitCounter1() {
        N = 300;
        hitMap = new HashMap<>();
        queue = new LinkedList<>();
    }

    public void hit(int timestamp) {
        move(timestamp);
        if (hitMap.containsKey(timestamp)) {
            int hit = hitMap.get(timestamp) + 1;
            hitMap.put(timestamp, hit);
        } else {
            hitMap.put(timestamp, 1);
            queue.offer(timestamp);
        }
    }

    void move(int timestamp) {
        while (!queue.isEmpty() && timestamp - queue.peek() >= N) {
            int time = queue.poll();
            hitMap.remove(time);
        }
    }

    public int getHits(int timestamp) {
        move(timestamp);
        int count = 0;
        Iterator<Integer> it = queue.iterator();

        while (it.hasNext()) {
            count += hitMap.getOrDefault(it.next(), 0);
        }

        return count;
    }
}
