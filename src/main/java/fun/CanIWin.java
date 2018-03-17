package fun;

import java.util.HashMap;
import java.util.Map;

public class CanIWin {
    //key is boolean[] used
    //IF the boolean[] is {false, false, true, true, false}, then we transfer it to an Integer with binary representation as 00110
    Map<Integer, Boolean> map = new HashMap<>();
    boolean[] used;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        //count from 1 to maxChoosableInteger
        int sum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
        if (desiredTotal > sum) {
            return false;
        }

        if (desiredTotal <= 0) {
            return true;
        }

        used = new boolean[maxChoosableInteger + 1];
        return helper(desiredTotal);
    }

    private boolean helper(int desiredTotal) {
        if (desiredTotal <= 0) {
            return false;
        }

        int key = hash(used);

        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            for (int i = 1; i < used.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    //if opponent win, try another card not used
                    boolean oppoWin = helper(desiredTotal - i);
                    if (!oppoWin) {
                        //If opponent always lose, then I win
                        map.put(key, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }
            map.put(key, false);
            return false;
        }
    }

    // transfer boolean[] to an Integer
    public int hash(boolean[] used) {
        int num = 0;
        for (boolean b : used) {
            num <<= 1;
            if (b) {
                num |= 1;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        CanIWin obj = new CanIWin();
        boolean test = obj.canIWin(15, 105);
        System.err.println(test);
    }

}
