package Graph;

import java.util.HashMap;
import java.util.Map;

public class ExcelSum {

    class Formula {
        Map<String, Integer> cells;
        int val;

        public Formula(int val) {
            this.val = val;
            this.cells = new HashMap<>();
        }

        public Formula(Map<String, Integer> cells, int val) {
            this.cells = cells;
            this.val = val;
        }
    }

    Formula[][] formulas;

    public ExcelSum(int H, char W) {
        formulas = new Formula[H][W - 'A' + 1];
    }

    public int get(int r, char c) {
        Formula f = getFormula(r, c);
        if (f == null) {
            return 0;
        } else {
            return f.val;
        }
    }

    private Formula getFormula(int r, char c) {
        return formulas[r - 1][c - 'A'];
    }

    public void set(int r, char c, int v) {
        formulas[r - 1][c - 'A'] = new Formula(v);//clear old formular
        topologicalSort(r - 1, c - 'A');
        executeStack();
    }

    public int sum(int r, char c, String[] strs) {
        Map<String, Integer> map = convertToMap(strs);
        int sum = calculateSum(r-1, c-'A', map);
        set(r, c, sum);
        Formula f = formulas[r - 1][c - 'A'];
        f.cells = map;
        f.val = sum;

        return sum;
    }

    void topologicalSort(int r, int c) {
        for (int i = 0; i < formulas.length; i++) {
            for (int j = 0; j < formulas[0].length; j++) {
                if (formulas[i][j] != null && formulas[i][j].cells)

            }
        }

    }

    void executeStack() {

    }

    public Map<String, Integer> convertToMap(String[] strs) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            if (!str.contains(":")) {
                map.put(str, map.getOrDefault(str, 0) + 1);
            } else {
                String[] cells = str.split(":");

                int startRow = cells[0].charAt(1) - '0';
                int endRow = cells[1].charAt(1) - '0';

                char startCol = cells[0].charAt(0);
                char endCol = cells[1].charAt(0);

                for (int i = startRow; i <= endRow; i++) {
                    for (char j = startCol; j <= endCol; j++) {

                        String cellName = getCellName(i, j);

                        map.put(cellName, map.getOrDefault(cellName, 0) + 1);
                    }
                }
            }
        }
        return map;
    }

    int calculateSum(int r, int c, Map<String, Integer> map) {
        int sum = 0;
        for (String s : map.keySet()) {
            int x = Integer.valueOf(s.substring(1)) - 1;
            int y = s.charAt(0) - 'A';
            Formula f = formulas[x][y];
            int val = f != null ? f.val : 0;
            int count = map.get(s);
            sum += val * count;
        }

        formulas[r][c] = new Formula(map, sum);
        return sum;
    }

    private String getCellName(int r, char c) {
        return "" + c + r;
    }
}
