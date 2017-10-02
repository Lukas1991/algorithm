import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class TestExample {


    private String encode(int[] arr) {
        return arr[0] + "," + arr[1];
    }

    @Test
    public void test() {
        Set<String> curr = new HashSet<>();
        curr.add(encode(new int[]{1,2}));
        curr.add(encode(new int[]{1,2}));
        System.err.println(knightProbability(3,2,0,0));
        System.err.println(knightProbability(1,1,0,0));
        System.err.println(knightProbability(10,13,5,3));
    }



    public double knightProbability(int N, int K, int r, int c) {
        double probability = 1.0;
        boolean moved = false;
        List<String> curr = new ArrayList<>();
        curr.add(r + "," + c);

        Map<String, List<String>> map = new HashMap<>();

        double div = 1.0;
        for (int i = 0; i < K; i++) {
            div *= 8.0;
            List<String> moves = new ArrayList<>();

            for (String s : curr) {
                if (map.containsKey(s)) {
                    moves.addAll(map.get(s));
                } else {
                    int[] arr = decode(s);
                    List<String> tmp = getMoves(N, arr[0], arr[1]);

                    moves.addAll(tmp);
                    map.put(s, tmp);
                }
            }

            if (moves.isEmpty()) {
                break;
            } else {
                moved = true;
                //remove dup
                Set<String> set = new HashSet<>(moves);
                curr = new ArrayList<>(set);
                //curr = moves;
            }
        }

        if (moved) {
            return curr.size() / div;
        } else {
            return 0.0;
        }

    }

    private List<String> getMoves(int N, int r, int c) {
        List<String> res = new ArrayList<>();

        int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};

        for (int i = 0; i < dx.length; i++) {
            int x = r + dx[i];
            int y = c + dy[i];

            if (x >= 0 && y >= 0 && x < N && y < N) {
                res.add(x + "," + y);
            }
        }

        return res;
    }

    private int[] decode(String s) {
        String[] arr = s.split(",");
        return new int[]{Integer.valueOf(arr[0]), Integer.valueOf(arr[1])};
    }
}
