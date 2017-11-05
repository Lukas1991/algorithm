package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//721. Accounts Merge
public class AccountsMerge {

    /**
     * Build a graph from email, and its neighbors
     * dfs find connected component
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap();

        //graph, key is email, value is neighbors
        Map<String, List<String>> emailsMap = new HashMap<>();

        //build a graph
        for (List<String> account : accounts) {
            String name = account.get(0);
            String rootEmail = account.get(1);

            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (!emailsMap.containsKey(email)) {
                    emailsMap.put(email, new ArrayList<>());
                }

                if (i > 1) {
                    emailsMap.get(email).add(rootEmail);
                    emailsMap.get(rootEmail).add(email);
                }

                emailToName.put(email, name);
            }
        }

        Set<String> visited = new HashSet<>();

        List<List<String>> res = new ArrayList<>();

        for (String email : emailsMap.keySet()) {
            if (visited.contains(email)) {
                continue;
            }

            List<String> tmp = new ArrayList<>();
            dfs(email, tmp, visited, emailsMap);

            Collections.sort(tmp);
            tmp.add(0, emailToName.get(email));

            res.add(tmp);
        }

        return res;
    }


    private void dfs(String start, List<String> tmp, Set<String> visited, Map<String, List<String>> emailsMap) {
        if (visited.contains(start)) {
            return;
        }

        visited.add(start);
        tmp.add(start);

        List<String> neighbors = emailsMap.get(start);
        for (String n : neighbors) {
            dfs(n, tmp, visited, emailsMap);
        }
    }

}
