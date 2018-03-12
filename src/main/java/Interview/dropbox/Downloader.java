package Interview.dropbox;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Downloader {


    public static boolean isFileDone(List<Range> blocks, int size) {
        if (blocks.size() == 0) {
            return size == 0;
        }
        //sort blocks by lower ASC
        blocks.sort((r1, r2) -> r1.lower - r2.lower);

        Range pre = blocks.get(0);
        if (pre.lower != 0) return false; //必须从0开始

        for (int i = 1; i < blocks.size(); i++) {
            Range range = blocks.get(i);

            if (range.lower <= pre.higher) {
                //merge
                pre.higher = Math.max(pre.higher, range.higher);
            } else {
                break;
            }
        }

        return pre.higher >= size;
    }

    // 0, 1,2,3,. ..7
    /* isFileDone([[3, 7), [0, 1), [2, 5), [6, 8)], 8) -> false */
    /* isFileDone([[3, 7), [0, 2), [2, 5), [6, 8)], 8) -> true */
    //blocks: [[0, 2), [2, 5), [3, 7),[6, 8)]
    public static void main(String[] args) {
        List<Range> blocks = new ArrayList<>();
        blocks.add(new Range(3, 7));
        blocks.add(new Range(0, 1)); //if change hgiher to 2, result is false
        blocks.add(new Range(2, 5));
        blocks.add(new Range(6, 8));

        System.err.println(isFileDone(new ArrayList<>(blocks), 8));//false

        Downloader obj = new Downloader(8);
        for (Range r : blocks) {
            obj.addBlock(r);
        }

        System.err.println(obj.isDone()); //false
    }


    PriorityQueue<Range> pq;
    int size;

    public Downloader(int size) {
        pq = new PriorityQueue<>((r1, r2) -> r1.lower - r2.lower);
        this.size = size;
    }

    public void addBlock(Range r) {
        pq.offer(r);
    }

    //O(N) //可以用TreeMap, 来一个，merge一个，做法如My Calandar. 插入时O(logN), 查询时O(1)
    public boolean isDone() {
        if (pq.size() == 0) {
            return this.size == 0;
        }

        Range pre = pq.poll();
        if (pre.lower != 0) return false;

        while (!pq.isEmpty()) {
            Range range = pq.poll();
            if (range.lower <= pre.higher) {
                pre.higher = Math.max(pre.higher, range.higher);
            } else {
                break;
            }
        }

        return pre.higher >= size;
    }

}

class Range {
    int lower;
    int higher;

    public Range(int lower, int higher) {
        this.lower = lower;
        this.higher = higher;
    }
}