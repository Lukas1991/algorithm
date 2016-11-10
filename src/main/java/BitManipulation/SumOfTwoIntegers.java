package BitManipulation;

public class SumOfTwoIntegers {

    //a^b 直接算出a+b 每位上%2的结果，
    //carry = (a & b)<<1, 只有两个位均为1才会进位, a&b 是否两位都是1, 左移表示进到下一位
    public int getSum(int a, int b) {
        int sum = a^b;
        int carry = (a&b) << 1;
        if (carry != 0) {
            return getSum(sum, carry);
        } else {
            return sum;
        }
    }
}
