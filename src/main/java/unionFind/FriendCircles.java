package unionFind;

import java.util.Arrays;

public class FriendCircles {
	public int findCircleNum(int[][] M) {
        int n = M.length;
        int count = n;
        
        int[] root = new int[n];
        Arrays.fill(root, -1);
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if(M[i][j] == 1) {
                    int r1 = find(root, i);
                    int r2 = find(root, j);
                    if (r1 != r2) {
                        count--;
                        root[r2] = r1;
                    }
                } 
            }
        }
        
        return count;
    }
    
    private int find(int[] root, int a) {
        if (root[a] == -1) {
            root[a] = a;
            return a;
        } else {
            while (root[a] != a) {
                root[a] = root[root[a]];
                a = root[a];
            }
            
            return a;
        }
    }

    //fill array with its index
    public int findCircleNum2(int[][] M) {
        int n = M.length;
        int count = n;
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) {
                    int root1 = find2(root, i);
                    int root2 = find2(root, j);

                    if (root1 != root2) {
                        //merge
                        root[root2] = root1;
                        count--;
                    }
                }
            }
        }

        return count;
    }

    private int find2(int[] root, int a) {
        while (root[a] != a) {
            int p = root[a];
            root[a] = root[p];
            a = root[a];
        }
        return a;
    }
}
