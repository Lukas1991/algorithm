package sweepLine;

import tools.Interval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessSequence {

    /**
     * There is a process sequence that contains the start and end of each process.
     * There is a query sequence asking how many processes are running at a certain point in time.
     * Please return the query result of the query sequence.
     * <p>
     * Given logs = [[1, 1234], [2, 1234]], queries = [1, 1235], return [1, 0].
     * Given logs = [[1, 1234], [2, 1234]], queries = [2], return [2].
     */
    public List<Integer> numberOfProcesses(List<Interval> logs, List<Integer> queries) {
        List<Event> events = new ArrayList<>();

        //end 0, start 1, in progress 2
        for (Interval in : logs) {
            events.add(new Event(in.end + 1, 0));
            events.add(new Event(in.start, 1));
        }

        for (int q : queries) {
            events.add(new Event(q, 2));
        }

        //sort by time ASC, then sort by flag ASC
        events.sort((e1, e2) -> {
            if (e1.time == e2.time) {
                return e1.flag - e2.flag;
            } else {
                return e1.time - e2.time;
            }
        });

        //key is query time, value is count
        Map<Integer, Integer> queryMap = new HashMap<>();
        int count = 0;

        for (Event e : events) {
            if (e.flag == 1) {
                count++;
            } else if (e.flag == 0) {
                count--;
            } else {
                queryMap.put(e.time, count);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int q : queries) {
            res.add(queryMap.get(q));
        }
        return res;
    }

    class Event {
        int time;
        int flag;

        public Event(int time, int flag) {
            this.time = time;
            this.flag = flag;
        }
    }
}
