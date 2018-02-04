package array;

import java.util.ArrayList;
import java.util.List;
import tools.Interval;

/**
 * Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?
 * If landing and flying happens at the same time, we consider landing should happen at first.
 */
public class NumberOfAirplanes {

    public int countOfAirplanes(List<Interval> airplanes) {
        List<Event> events = new ArrayList<>();
        for (Interval in : airplanes) {
            events.add(new Event(in.start, 1));
            events.add(new Event(in.end, 0));
        }

        //sort by time ASC, then sort by flag ASC. 先降落再起飞
        events.sort((e1, e2) -> {
            if (e1.time == e2.time) {
                return e1.flag - e2.flag;
            } else {
                return e1.time - e2.time;
            }
        });

        int count = 0;
        int max = 0;
        for (Event e : events) {
            if (e.flag == 1) {
                count++;
            } else {
                count--;
            }
            max = Math.max(max, count);
        }

        return max;
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
