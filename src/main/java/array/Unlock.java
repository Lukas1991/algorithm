package array;

/**
 * Given n keys(numbered from 1 to n) and m locks(numbered from 1 to m). When the number of the lock can be divisible by the number of the key, the lock can be opened/closed. Initially, all locks are locked, then use all keys to unlock all locks, find the number of locks which is opened in the end.
 * <p>
 * Example
 * Given n = 1, m = 1, return 1.
 * The lock numbered 1 has been unlocked.
 * <p>
 * Given n = 2, m = 5, return 3.
 * The locks numbered 1,3,5 have been opened.
 * <p>
 * Linkedin
 */
public class Unlock {

    /**
     * @param n: the number of keys
     * @param m: the number of locks
     * @return: the numbers of open locks
     */
    public int unlock(int n, int m) {
        boolean[] locks = new boolean[m + 1];

        for (int k = 1; k <= n; k++) {
            for (int l = k; l <= m; l += k) {
                locks[l] = !locks[l];
            }
        }

        int count = 0;
        for (int l = 1; l <= m; l++) {
            if (locks[l]) {
                count++;
            }
        }

        return count;
    }
}
