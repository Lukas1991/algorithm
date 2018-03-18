package dp;

/**
 * 微软面经, 背景是用剪刀在magzine上剪英文单词或者字母，假设剪一个单词下来需要剪4刀。输入是一个String magzine和一个string note,
 * 要求用minnimum cut从magzine中剪出letters然后拼成note。
 * 比如说magzine是"Hi Bob", note是"Bob", min cut就是4, 因为只要把Bob这个单词减下来就行了。
 * 如果note是"ib"，那么min cut就是8，因为需要分别把i和b剪下来。
 */
public class MagazineMinCut {

    public static int getMinCut(String magazine, String note) {
        int n = magazine.length();
        int m = note.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (magazine.charAt(i - 1) == note.charAt(j - 1)) {
                    if (i - 2 >= 0 && j - 2 >= 0 && magazine.charAt(i - 2) == note.charAt(j - 2)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {

                        dp[i][j] = dp[i - 1][j];

                        if (dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                            dp[i][j] = Math.min(dp[i - 1][j - 1] + 4, dp[i][j]);
                        }
                    }

                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(getMinCut("o b o b ", "ob")); //8

        System.out.println(getMinCut("Hi Bob", "Hi")); //4
        System.out.println(getMinCut("Hi Bob", "Bob")); //4
        System.out.println(getMinCut("Hi Bob", "ib")); //8
        System.out.println(getMinCut("Hi Bob", "iB")); //8
        System.out.println(getMinCut("Hi Bob", "IB")); //2147483647
        System.out.println(getMinCut("Hi Bob", "bob")); //2147483647


        System.out.println(getMinCut("aaaaa", "aaaa")); //4
        System.out.println(getMinCut("aaaaa", "aa")); //4

        System.out.println(getMinCut("aabaa", "aaaa")); //8
        System.out.println(getMinCut("aabaa", "aa")); //4

        System.out.println(getMinCut("aabaa", "xyz")); //2147483647
    }
}
