package fun;

import java.util.ArrayList;
import java.util.List;

public class JudgePoint24 {
    public boolean judgePoint24(int[] nums) {
        int a = nums[0];
        int b = nums[1];
        int c = nums[2];
        int d = nums[3];

        boolean test = judgeGroupTwo(a, b, c, d) ||
            judgeGroupTwo(a, c, b, d) ||
            judgeGroupTwo(a, d, b, c);

        if (test)   return true;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) continue;

                for (int k = 0; k < 4; k++) {
                    if (k == i || k == j) continue;

                    for (int p = 0; p < 4; p++) {
                        if (p == i || p == j || p == k) continue;

                        if(judgeByOrder(nums[i], nums[j], nums[k], nums[p])) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean judgeByOrder(int a, int b, int c, int d) {
        List<Double> left = cal(a, b);
        List<Double> left2 = cal(left, c);
        List<Double> res = cal(left2, d);
        return judge(res);
    }

    private boolean judgeGroupTwo(int a, int b, int c, int d) {
        List<Double> left = cal(a, b);
        List<Double> right = cal(c, d);

        for (int i = 0; i < left.size(); i++) {
            for (int j = 0; j < right.size(); j++) {
                List<Double> res = cal(left.get(i), right.get(j));
                if (judge(res)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean judge(List<Double> res) {
        for (double r : res) {
            if (r == 24.0) {
                return true;
            }
        }
        return false;
    }

    private List<Double> cal(List<Double> res, int a) {
        List<Double> list = new ArrayList<>();
        for(double d : res) {
            list.addAll(cal(d, a));
        }
        return list;
    }

    private List<Double> cal(double a1, double b1) {
        List<Double> list = new ArrayList<>();
        list.add( a1 + b1);
        list.add( a1 - b1);
        list.add( a1 * b1);
        list.add( a1 / b1);

        list.add( b1 - a1);
        list.add( b1 / a1);
        return list;
    }

    private List<Double> cal(int a, int b) {
        double a1 = a;
        double b1 = b;

        return cal(a1, b1);
    }

    public static void main(String[] args) {
        JudgePoint24 obj = new JudgePoint24();
        System.err.println(obj.judgePoint24(new int[]{4, 1, 8, 7}));  //t
        System.err.println(obj.judgePoint24(new int[]{1, 2, 1, 2}));  //f

        System.err.println(obj.judgePoint24(new int[]{1, 3, 4, 6}));  //true
        System.err.println(obj.judgePoint24(new int[]{1, 5, 9, 1}));  //false

        System.err.println(obj.judgePoint24(new int[]{3, 9, 7, 7}));  //true
        System.err.println(obj.judgePoint24(new int[]{1, 9, 1, 2}));  //true
    }
}
