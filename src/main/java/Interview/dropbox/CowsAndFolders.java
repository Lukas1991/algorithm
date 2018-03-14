package Interview.dropbox;

import java.util.*;

public class CowsAndFolders {

    static class Folder {
        int id = 0;
        boolean isShared = false; // true: shared, false: confidential
        List<Folder> children = new LinkedList<>();
        List<Integer> cows = new LinkedList<>();
        int in = 0;
        int out = 0;

        Folder(int id, boolean isShared) {
            this.id = id;
            this.isShared = isShared;
            in = 0;
            out = 0;
        }

        void appendCow(List<Integer> newCows) {
            cows.addAll(newCows);
        }
    }

    private static void traverse(int[] cows, Folder root) {
        //if is leaf
        if (root.out == 0) {
            for (int i : root.cows) {
                cows[i]++;
            }
        }

        for (Folder child : root.children) {
            if (child.isShared) {
                child.appendCow(root.cows);
            }

            traverse(cows, child);
        }
    }

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Folder> folderMap = new HashMap<>();

        // parse and build folders
        int Q = scanner.nextInt(); //number of cows
        int M = scanner.nextInt(); // number of shared folder
        int N = scanner.nextInt(); //number of confidential folder
        while (M-- > 0) {
            Folder folder = new Folder(scanner.nextInt(), true);
            folderMap.put(folder.id, folder);
            int K = scanner.nextInt();
            while (K-- > 0)
                folder.cows.add(scanner.nextInt());
        }
        while (N-- > 0) {
            Folder folder = new Folder(scanner.nextInt(), false);
            folderMap.put(folder.id, folder);
            int K = scanner.nextInt();
            while (K-- > 0)
                folder.cows.add(scanner.nextInt());
        }

        // parse and build trees
        int G = scanner.nextInt();
        while (G-- > 0) {
            int U = scanner.nextInt();
            int V = scanner.nextInt();
            Folder u = folderMap.get(U);
            Folder v = folderMap.get(V);
            if (u != null & v != null) {
                u.children.add(v);
                u.out++;
                v.in++;
            }
        }

        // traverse
        int numLeaf = 0;
        int[] cows = new int[Q];

        for (Folder folder : folderMap.values()) {
            //is a root
            if (folder.in == 0) {
                traverse(cows, folder);
            }

            //is leaf
            if (folder.out == 0) {
                numLeaf++;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            if (cows[i] < numLeaf)
                res.add(i);
        }

        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i) + " ");
        }
    }
}
