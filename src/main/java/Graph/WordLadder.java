package Graph;

import java.util.*;

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


    /**
     * Word Ladder 1
     * BFS, Level by level, for each level use Set<String> queue, and nextQueue
     * To avoid Time Limit Exceeded, convert wordList to a set, wordSet.contains(newStr) will take O(1)
     * To avoid infinite loop, once we find a transformed word, remove it from wordSet. We can also use a visited set
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
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
                                wordSet.remove(newString);
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
