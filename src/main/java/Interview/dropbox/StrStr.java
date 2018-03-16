package Interview.dropbox;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

//* / % same level of precedence

/**
 * powers = 31 ^ target length
 * hash = (hash * 31 + ADD) % mod
 * hash = hash - DELETE * powers % mode
 * if (hash < 0) {
 * hash += mode;
 * }
 */
public class StrStr {

    public int strStr1(String source, String target) {
        if (source == null || target == null) return -1;
        if (target.length() == 0) return 0;

        for (int i = 0; i <= source.length() - target.length(); i++) {
            int j = 0;
            for (; j < target.length(); j++) {
                if (source.charAt(i + j) != target.charAt(j)) {
                    break;
                }
            }

            //target all match
            if (j == target.length()) {
                return i;
            }
        }

        return -1;
    }

    public static final int BASE = 1000000; //HASH_SIZE is the capacity of the hash table

    public int strStr2(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }

        int m = target.length();
        if (m == 0) {
            return 0;
        }

        // a * 31 ^ m
        int power = 1;
        for (int i = 0; i < m; i++) {
            power = (power * 31) % BASE;
        }

        int targetHash = 0;
        for (int i = 0; i < m; i++) {
            targetHash = (targetHash * 31 + target.charAt(i)) % BASE;
        }

        int hash = 0;
        for (int i = 0; i < source.length(); i++) {
            //abc + d
            hash = (hash * 31 + source.charAt(i)) % BASE;
            //abcd - a
            if (i - m >= 0) {
                hash = hash - (source.charAt(i - m) * power) % BASE;
                if (hash < 0) {
                    hash += BASE;
                }
            }

            //double check the substring, to avoid false positive(the test result is true, but it actually should be false)
            if (hash == targetHash) {
                String sub = source.substring(i - m + 1, i + 1);
                if (sub.equals(target)) {
                    return i - m + 1;
                }
            }
        }

        return -1;
    }

    public int hashCode(char[] key, int HASH_SIZE) {
        long hash = 0;
        for (int i = 0; i < key.length; i++) {
            hash = (hash * 33 + key[i]) % HASH_SIZE;
        }

        return (int) hash;
    }

    public boolean match(byte[] target, byte[] source) {

        int m = target.length;
        int targetHash = 0;
        for (int i = 0; i < m; i++) {
            targetHash = (targetHash * 31 + target[i]) % BASE;
        }

        //31^m
        int power = 1;
        for (int i = 0; i < m; i++) {
            power = power * 31 % BASE;
        }

        int hash = 0;
        for (int i = 0; i < source.length; i++) {
            hash = (hash * 31 + source[i]) % BASE;
            if (i - m >= 0) {
                hash = hash - source[i - m] * power % BASE;
                if (hash < 0) {
                    hash += BASE;
                }
            }

            if (hash == targetHash) {
                if (compare(source, target, i - m + 1, i, 0, m - 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    //If file is too large, read N bytes every time to a buffer
    public boolean matchFileTooLarge(String filePath, byte[] target) throws IOException {
        int m = target.length;
        int targetHash = 0;
        for (int i = 0; i < m; i++) {
            targetHash = (targetHash * 31 + target[i]) % BASE;
        }

        //31^m
        int power = 1;
        for (int i = 0; i < m; i++) {
            power = power * 31 % BASE;
        }

        int hash = 0;
        int read;

        int N = 1024;//N > m
        byte[] buffer = new byte[N];
        byte[] preBuffer = new byte[N]; //size can also be m. have to shift last m bytes in buffer to preBuffer
        int readTimes = 0;

        FileInputStream inputStream = new FileInputStream(filePath);
        do {

            read = inputStream.read(buffer);
            readTimes++;

            for (int i = 0; i < Math.min(buffer.length, read); i++) {
                hash = (hash * 31 + buffer[i]) % BASE;

                if (i - m >= 0) {
                    hash = hash - buffer[i - m] * power % BASE;
                    if (hash < 0) {
                        hash += BASE;
                    }

                    if (hash == targetHash) {
                        if (compare(buffer, target, i - m + 1, i, 0, m - 1)) {
                            return true;
                        }
                    }

                } else if (readTimes > 1) {
                    hash = hash - preBuffer[N - m + i] * power % BASE;
                    if (hash < 0) {
                        hash += BASE;
                    }

                    if (hash == targetHash) {
                        if (compare(preBuffer, target, N - m + i + 1, N - 1, 0, m - i - 2) &&
                                compare(buffer, target, 0, i, m - i - 1, m - 1)) {
                            return true;
                        }
                    }
                }
            }

            preBuffer = buffer.clone();

        } while (read != -1);

        return false;
    }

    boolean compare(byte[] source, byte[] target, int sStart, int sEnd, int tStart, int tEnd) {
        if ((sEnd - sStart) != (tEnd - tStart)) {
            return false;
        }

        int i = sStart;
        int j = tStart;

        while (i <= sEnd) {
            if (source[i] != target[j]) {
                return false;
            } else {
                i++;
                j++;
            }
        }

        return true;
    }


    //byte[] target = targetStr.getBytes();
    //Read 1 byte a time
    public boolean matchFileTooLarge2(String filePath, byte[] target) throws IOException {
        int m = target.length;
        int targetHash = 0;
        for (int i = 0; i < m; i++) {
            targetHash = (targetHash * 31 + target[i]) % BASE;
        }

        //31^m
        int power = 1;
        for (int i = 0; i < m; i++) {
            power = power * 31 % BASE;
        }

        Queue<Byte> queue = new LinkedList<>();

        FileInputStream inputStream = null;
        int hash = 0;
        int read = 0;
        byte b1;

        try {
            inputStream = new FileInputStream(filePath);

            while ((read = inputStream.read()) != -1) {

                b1 = (byte) read;
                queue.offer(b1);
                hash = (hash * 31 + b1) % BASE;

                if (queue.size() > m) {
                    int front = queue.poll();
                    hash = hash - front * power % BASE;
                    if (hash < 0) {
                        hash += BASE;
                    }
                }

                if (hash == targetHash) {
                    if (compare(queue, target)) {
                        return true;
                    }
                }
            }
        } catch (IOException ex) {

        } finally {
            if (inputStream != null)
                inputStream.close();
        }

        return false;
    }

    boolean compare(Queue<Byte> queue, byte[] target) {
        if (queue.size() != target.length) return false;

        Iterator<Byte> it = queue.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next() != target[i]) {
                return false;
            }
            i++;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        StrStr obj = new StrStr();
        //String target = "Regular Expression Matching";
        String target = "Valid Sudoku";

        String filePath = "/Users/chuyu/gitRepo/algorithm/src/main/resources/data.txt";

        byte[] source = Files.readAllBytes(Paths.get(filePath));

        boolean match = obj.match(target.getBytes(), source);
        System.err.println(match);

        match = obj.matchFileTooLarge(filePath, target.getBytes());
        System.err.println(match);

        match = obj.matchFileTooLarge2(filePath, target.getBytes());
        System.err.println(match);
    }
}
