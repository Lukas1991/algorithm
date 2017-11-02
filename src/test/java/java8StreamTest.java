import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;

public class java8StreamTest {

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 4};
        Arrays.stream(arr).sum();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(7);

        int max = list.stream().max(Comparator.naturalOrder()).get();
        System.err.println(max);

        int sum = list.stream().mapToInt(Integer::intValue).sum();
        System.err.println(sum);
    }

    @Test
    public void groupingBy() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Map<Integer, List<Integer>> map = list.stream().collect(Collectors.groupingBy(i -> i % 2));

        System.err.println(map.toString());
    }

    @Test
    public void distinct() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);

        List<Integer> distinctList = list.stream().distinct().collect(Collectors.toList());
        System.err.println(distinctList.toString());
    }
}
