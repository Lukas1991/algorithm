package BitManipulation;

import java.util.ArrayList;
import java.util.List;

public class IpToCIDR {

    public List<String> ipToCIDR(String ip, int n) {
        long start = ipToLong(ip);
        List<String> ans = new ArrayList();

        while (n > 0) {
            long lowestOneBit = Long.lowestOneBit(start); //start & -start
            int mask = Math.max(33 - bitLength(lowestOneBit), 33 - bitLength(n));

            ans.add(longToIP(start) + "/" + mask);

            start += 1 << (32 - mask);
            n -= 1 << (32 - mask);
        }
        return ans;
    }

    private long ipToLong(String ip) {
        long ans = 0;
        for (String x : ip.split("\\.")) {
            ans = 256 * ans + Integer.valueOf(x);
        }
        return ans;
    }

    private String longToIP(long x) {
        return String.format("%s.%s.%s.%s",
            x >> 24, (x >> 16) % 256, (x >> 8) % 256, x % 256);
    }

    //lenght start from left most bit 1 to the right
    private int bitLength(long x) {
        if (x == 0) {
            return 1;
        }
        int ans = 0;
        while (x > 0) {
            x >>= 1;
            ans++;
        }
        return ans;
    }

    //helper class to print out
    private static String toBinay(int a) {
        char[] array = new char[8];

        for (int i = 0; i < 8; i++) {
            if (a % 2 == 0)
                array[8- i - 1] = '0';
            else if (a % 2 == 1)
                array[8- i - 1] = '1';
            a = a / 2;
        }
        return new String(array);
    }

    public static void main(String[] args) {
        IpToCIDR obj = new IpToCIDR();

        int a = 7;

        System.err.println(toBinay(a)); //00000111
        System.err.println(toBinay(a & -a));//00000001

        System.err.println(obj.bitLength(a & -a)); //bitLength = 1
        System.err.println(obj.bitLength(10)); //bitLength = 4

        System.err.println(obj.ipToCIDR("255.0.0.7",10).toString());
        System.err.println(obj.ipToCIDR("117.145.102.62",8).toString());
    }

}
