package trie;

import java.util.ArrayList;
import java.util.List;

public class BoggleGame {

    TrieNode root;

    public int boggleGame(char[][] board, String[] words) {
        root = new TrieNode();
        for (String word : words) {
            insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        List<String> result = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];

        findWords(result, board, visited, new ArrayList<>(), 0, 0);
        return result.size();
    }

    //DFS 找以(x,y)为头，找能组合成有多少个words
    public void findWords(List<String> result, char[][] board, boolean[][] visited, List<String> tmpWords, int x, int y) {

        int m = board.length;
        int n = board[0].length;
        for (int i = x; i < m; i++) { //以(x,y)为头
            for (int j = y; j < n; j++) {
                List<List<Cell>> nextWordIndexes = new ArrayList<>();

                getNextWords(nextWordIndexes, board, visited, new ArrayList<>(), i, j, root);

                for (List<Cell> indexes : nextWordIndexes) {
                    String word = "";
                    for (Cell cell : indexes) {
                        visited[cell.i][cell.j] = true;
                        word += board[cell.i][cell.j];
                    }

                    tmpWords.add(word);

                    if (tmpWords.size() > result.size()) {
                        result.clear();
                        result.addAll(tmpWords);
                    }

                    findWords(result, board, visited, tmpWords, i, j);

                    tmpWords.remove(tmpWords.size() - 1);
                    for (Cell cell : indexes) {
                        visited[cell.i][cell.j] = false;
                    }
                }
            }
            y = 0;  //scan the first col in next row
        }
    }

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    //DFS 找以（i, j）为头的所有单词的index，例如abc, adg, 两组indexes
    private void getNextWords(List<List<Cell>> wordsIndexes, char[][] board,
                              boolean[][] visited, List<Cell> path, int i, int j, TrieNode root) {
        if (i < 0 | i >= board.length || j < 0 || j >= board[0].length
            || visited[i][j] == true || root.children[board[i][j] - 'a'] == null) {
            return;
        }

        root = root.children[board[i][j] - 'a'];
        if (root.isWord) {
            List<Cell> newPath = new ArrayList<>(path);
            newPath.add(new Cell(i, j));
            wordsIndexes.add(newPath);
            return;
        }

        visited[i][j] = true;
        path.add(new Cell(i, j));
        for (int k = 0; k < 4; k++) {
            getNextWords(wordsIndexes, board, visited, path, i + dx[k], j + dy[k], root);
        }
        path.remove(path.size() - 1);
        visited[i][j] = false;
    }

    class TrieNode {
        boolean isWord;
        TrieNode[] children;

        TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        node.isWord = true;
    }

    class Cell {
        int i;
        int j;

        public Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        BoggleGame obj = new BoggleGame();
        char[][] board = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'}
        };
        String[] words = {"abc", "adg", "de", "dg", "defi", "gh"};
        System.err.println(obj.boggleGame(board, words));
    }
}
