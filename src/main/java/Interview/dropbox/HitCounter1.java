package Interview.dropbox;

import java.util.*;

public class HitCounter1 {

//    int TIME_WINDOW;
//    private Queue<Integer> q;
//
//    public HitCounter(){
//        TIME_WINDOW = 300;
//        q = new LinkedList<>();
//    }
//
//    public void hit(int timestamp){
//        q.offer(timestamp);
//    }
//
//    public int getHits(int timestamp){
//        while(!q.isEmpty() && timestamp - q.peek() >= TIME_WINDOW)
//            q.poll();
//
//        return q.size();
//    }

    int N;
    //key is time, value is count of hits
    Map<Integer, Integer> hitMap;
    //queue store timeStamps
    Queue<Integer> queue;

    public HitCounter1() {
        N = 300;
        hitMap = new HashMap<>();
        queue = new LinkedList<>();
    }

    public void hit(int timestamp) {
        move(timestamp);//也可以不move

        if (hitMap.containsKey(timestamp)) {
            hitMap.put(timestamp, hitMap.get(timestamp) + 1);
        } else {
            hitMap.put(timestamp, 1);

            queue.offer(timestamp);
        }
    }

    //remove stale timestamp
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
