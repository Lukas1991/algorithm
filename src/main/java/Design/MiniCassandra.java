package Design;

import java.util.*;

public class MiniCassandra {

    private Map<String, TreeMap<Integer, String>> hash;

    public MiniCassandra() {
        hash = new HashMap<>();
    }

    public void insert(String raw_key, int column_key, String column_value) {
        hash.putIfAbsent(raw_key, new TreeMap<>());
        hash.get(raw_key).put(column_key, column_value);
    }

    //Range query, eturn a list of entries
    public List<Column> query(String raw_key, int column_start, int column_end) {
        List<Column> res = new ArrayList<>();
        if (!hash.containsKey(raw_key))
            return res;

        NavigableMap<Integer, String> subMap = hash.get(raw_key)
                .subMap(column_start, true, column_end, true);

        for (Map.Entry<Integer, String> entry : subMap.entrySet()) {
            res.add(new Column(entry.getKey(), entry.getValue()));
        }

        return res;
    }

    public class Column {
        public int key;
        public String value;

        public Column(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
