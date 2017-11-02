package StackAndQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReconstructQueue {

    public int[][] reconstructQueue(int[][] people) {
        if (people.length <= 1) {
            return people;
        }

        int[][] res = new int[people.length][2];
        //sort by height DESC. for same height, sort by k ASC
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o2[0] - o1[0];
            } else {
                return o1[1] - o2[1];
            }
        });

        List<int[]> resList = new ArrayList<>();

        for (int i = 0; i < people.length; i++) {
            int[] p = people[i];
            resList.add(p[1], p);
        }

        for (int i = 0; i < people.length; i++) {
            res[i] = resList.get(i);
        }

        return res;
    }
}
