package Graph;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CanTakeCourse {

    //map key是course, value 是 prerequisites the course has
    List<Integer> canTake(int course, Map<Integer, List<Integer>> courseInfo) {
        List<Integer> res = new ArrayList<>();

        if (canTake(course, courseInfo, res, new HashSet<>(), new HashSet<>())) {
            return res;
        } else {
            return new ArrayList<>();
        }
    }

    private boolean canTake(int course, Map<Integer, List<Integer>> courseInfo, List<Integer> res, Set<Integer> taken, Set<Integer> parents) {

        if (taken.contains(course)) {
            return true;
        }

        //has circle
        if (parents.contains(course)) {
            return false;
        }

        List<Integer> pre = courseInfo.get(course);
        if (pre == null || pre.isEmpty()) {
            taken.add(course);
            res.add(course);
            return true;
        } else {

            parents.add(course);

            for (int preCourse : pre) {
                if (!canTake(preCourse, courseInfo, res, taken, parents)) {
                    return false;
                }
            }

            taken.add(course);
            res.add(course);
            return true;
        }
    }

    public static void main(String[] args) {
        CanTakeCourse obj = new CanTakeCourse();
        Map<Integer, List<Integer>> courseInfo = new HashMap<>();
        courseInfo.put(1, Lists.newArrayList(0, 3));
        courseInfo.put(2, Lists.newArrayList(0, 1));
        courseInfo.put(3, Lists.newArrayList(0, 1));

        System.out.println("Result: 0" + obj.canTake(0, courseInfo).toString());
        System.out.println("Result: 1" + obj.canTake(1, courseInfo).toString());
        System.out.println("Result: 2" + obj.canTake(2, courseInfo).toString());
        System.out.println("Result: 3" + obj.canTake(3, courseInfo).toString());
    }
}
