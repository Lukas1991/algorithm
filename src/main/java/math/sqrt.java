package math;

public class sqrt {

    /**
     * Use long for mid to avoid mid*mid from overflow.
     *
     * last position of mid, mid*mid <= x
     */
    public int sqrtBest(int x) {
        if (x == 0) {
            return 0;
        }

        long start = 1;
        long end = x;

        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            double p = mid * mid;
            if (p > x) {
                end = mid;
            } else if (p < x) {
                start = mid;
            } else if (p == x) {
                return (int) mid;
            }
        }

        if (end * end <= x) {
            return (int) end;
        } else {
            return (int) start;
        }
    }

    public boolean isPerfectSquare(int num) {
        long left = 1;
        long right = num;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long a = mid * mid;
            if (a == num) {
                return true;
            } else if (a > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    //Xk+1 = (Xk + n/Xk) / 2
    public int sqrtNewton(int x) {
        if (x == 0) {
            return 0;
        }

        long r = x;
        while (r * r > x) {
            r = (r + x / r) / 2;
        }

        return (int) r;
    }

}