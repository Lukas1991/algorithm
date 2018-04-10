package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordLadder {

    /**
     * 双向BFS，start queue, end queue， visited
     *
     * 先BFS start queue, if end queue contains the transformed word, then that's the answer.
     * 不存在的话，放到next queue里面，visited.add
     *
     * 比较next queue和end queue，小的作为下一次的start queue, 另一个是end queue.
     */
    public int ladderLengthBiDirection(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Set<String> startQ = new HashSet<>(), endQ = new HashSet<>();
        Set<String> visited = new HashSet<>();
        startQ.add(beginWord);

        if (dict.contains(endWord)) {
            endQ.add(endWord); // all transformed words must be in dict (including endWord)
        } else {
            return 0;
        }

        int depth = 1;
        while (!startQ.isEmpty()) {
            Set<String> nextQ = new HashSet<>();
            for (String word : startQ) {
                char[] arr = word.toCharArray();

                for (int j = 0; j < word.length(); j++) {
                    char before = word.charAt(j);

                    for (char c = 'a'; c <= 'z'; c++) {
                        // beginWord and endWord not the same, so bypass itself
                        if (c != before) {
                            arr[j] = c;
                            String newWord = new String(arr);

                            // meet from two ends
                            if (endQ.contains(newWord)) {
                                return depth + 1;
                            }

                            // not meet yet
                            if (dict.contains(newWord) && !visited.contains(newWord)) {
                                nextQ.add(newWord);
                                visited.add(newWord);
                            }
                        }
                    }

                    arr[j] = before;
                }
            }

            if (nextQ.size() < endQ.size()) {
                startQ = nextQ;
                endQ = endQ;
            } else {
                startQ = endQ;
                endQ = nextQ;
            }

            depth++;
        }

        return 0;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        if (!dict.contains(endWord)) {
            return res;
        }

        // hash set for both ends
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        // initial words in both ends
        set1.add(beginWord);
        set2.add(endWord);

        // we use a map to help construct the final result
        //key is start word, value is its next level word
        Map<String, List<String>> map = new HashMap<>();

        // build the map
        helper(dict, set1, set2, map, false);


        List<String> sol = new ArrayList<>();
        sol.add(beginWord);

        //recursively/backtracking build the final result
        generateList(beginWord, endWord, map, sol, res);

        return res;
    }

    boolean helper(Set<String> dict, Set<String> set1, Set<String> set2, Map<String, List<String>> map, boolean flip) {
        if (set1.isEmpty()) {
            return false;
        }

        if (set1.size() > set2.size()) {
            return helper(dict, set2, set1, map, !flip);
        }

        // remove words on current both ends from the dict
        dict.removeAll(set1);
        dict.removeAll(set2);

        // as we only need the shortest paths
        // we use a boolean value help early termination
        boolean done = false;

        // set for the next level
        Set<String> nextSet = new HashSet<>();

        // for each string in end 1
        for (String str : set1) {
            for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();

                // change one character for every position
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;

                    String word = new String(chars);

                    // make sure we construct the tree in the correct direction
                    String key = flip ? word : str;
                    String val = flip ? str : word;

                    if (!map.containsKey(key)) {
                        map.put(key, new ArrayList<>());
                    }

                    List<String> list = map.get(key);

                    if (set2.contains(word)) {
                        done = true;
                        list.add(val);
                    }

                    if (!done && dict.contains(word)) {
                        nextSet.add(word);
                        list.add(val);
                    }
                }
            }
        }

        // early terminate if done is true
        return done || helper(dict, set2, nextSet, map, !flip);
    }

    void generateList(String start, String end, Map<String, List<String>> map, List<String> sol, List<List<String>> res) {
        if (start.equals(end)) {
            res.add(new ArrayList<>(sol));
            return;
        }

        // need this check in case the diff between start and end happens to be one
        // e.g "a", "c", {"a", "b", "c"}
        if (!map.containsKey(start)) {
            return;
        }

        for (String word : map.get(start)) {
            sol.add(word);
            generateList(word, end, map, sol, res);
            sol.remove(sol.size() - 1);
        }
    }


    /**
     * Word Ladder 1
     * BFS, Level by level, for each level use Set<String> queue, and nextQueue
     * To avoid Time Limit Exceeded, convert wordList to a set, wordSet.contains(newStr) will take O(1)
     * To avoid infinite loop, once we find a transformed word, remove it from wordSet. We can also use a visited set
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        //加不加在这句，视题目而定
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Set<String> queue = new HashSet<>();
        queue.add(beginWord);

        int depth = 1;
        while (!queue.isEmpty()) {
            Set<String> nextQueue = new HashSet<>();

            for (String str : queue) {
                char[] arr = str.toCharArray();
                for (int i = 0; i < str.length(); i++) {
                    char before = arr[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != before) {
                            arr[i] = c;
                            String newString = new String(arr);

                            if (newString.equals(endWord)) {
                                return depth + 1;
                            }

                            if (wordSet.contains(newString)) {
                                nextQueue.add(newString);
                                wordSet.remove(newString); //!!!remove from set
                            }
                        }
                    }

                    arr[i] = before;
                }
            }

            queue = nextQueue;
            depth++;
        }

        return 0;
    }

    class WordNode {
        String word;
        int steps;
        WordNode pre;

        public WordNode(String word, int steps, WordNode pre) {
            this.word = word;
            this.steps = steps;
            this.pre = pre;
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        LinkedList<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1, null));

        wordList.add(endWord);
        int minStep = 0;

        Set<String> visited = new HashSet<>();
        Set<String> unVisited = new HashSet<>();
        unVisited.addAll(wordList);

        int preNumSteps = 0;

        while (!queue.isEmpty()) {
            WordNode node = queue.pop();
            int currentSteps = node.steps;
            String word = node.word;

            if (word.equals(endWord)) {
                if (minStep == 0) {
                    minStep = currentSteps; //is 1
                }

                if (currentSteps == minStep) {
                    List<String> list = new ArrayList<>();
                    list.add(word);
                    while (node.pre != null) {
                        node = node.pre;
                        list.add(0, node.word); //add to first
                    }
                    res.add(list);
                    continue;
                }
            }

            if (preNumSteps < currentSteps) {
                unVisited.removeAll(visited);
            }
            preNumSteps = currentSteps;

            char[] arr = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char tmp = arr[i];
                    if (arr[i] != c) {
                        arr[i] = c;
                    }
                    String newWord = new String(arr);
                    if (unVisited.contains(newWord)) {
                        queue.add(new WordNode(newWord, currentSteps + 1, node));
                        visited.add(word);
                    }
                    arr[i] = tmp;
                }

            }

        }

        return res;
    }


    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();

        String[] arr = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = Arrays.asList(arr);

        int minStep = wordLadder.ladderLength("hit", "cog", wordList);
        //int minStep = wordLadder.ladderLengthQueue("hit", "cog", wordList);
        System.err.println(minStep);
    }

}
