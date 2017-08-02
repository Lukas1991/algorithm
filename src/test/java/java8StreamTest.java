import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
}
