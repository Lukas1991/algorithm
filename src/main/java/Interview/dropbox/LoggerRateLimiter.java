package Interview.dropbox;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 359. Logger Rate Limiter
 * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
 *
 * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
 *
 * It is possible that several messages arrive roughly at the same time.
 */
public class LoggerRateLimiter {

    //------------------------Map store when it’s ok for a message to be printed again.--------------------------------------------
    //key is next print time
    private Map<String, Integer> ok = new HashMap<>();

    //ok map will blew up
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (timestamp < ok.getOrDefault(message, 0)) {
            return false;
        } else {
            ok.put(message, timestamp + 10);
            return true;
        }
    }

    //------------------------------------------------------------------------
    Queue<LogMsg> queue = new LinkedList<>();

    //queue will too large if too many same message within 10s
    public boolean shouldPrintMessage2(int timestamp, String message) {
        while (!queue.isEmpty() && timestamp - queue.peek().time >= 10) {
            queue.poll();
        }

        Iterator<LogMsg> it = queue.iterator();
        while (it.hasNext()) {
            LogMsg logMsg = it.next();
            if (logMsg.msg.equals(message)) {
                return false;
            }
        }

        queue.offer(new LogMsg(timestamp, message));
        return true;
    }

    class LogMsg {
        int time;
        String msg;

        public LogMsg(int time, String msg) {
            this.time = time;
            this.msg = msg;
        }
    }

    //----------------------Circular Array --------------------------------------------------
    Set<String>[] array = new Set[10];
    int[] times = new int[10];

    public LoggerRateLimiter() {
        for (int i = 0; i < 10; i++) {
            array[i] = new HashSet<>();
        }
    }

    public boolean shouldPrintMessage3(int timestamp, String message) {
        int index = timestamp % 10;

        boolean contains = false;
        for (int i = 0; i < 10; i++) {
            if (timestamp - times[i] < 10) {
                if (array[i].contains(message)) {
                    contains = true;
                    break;
                }
            }
        }

        if (!contains) {
            if (times[index] != timestamp) {
                times[index] = timestamp;
                array[index].clear();
            }

            array[index].add(message);
        }

        return !contains;
    }
}
