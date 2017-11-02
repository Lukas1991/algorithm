package math;

import java.util.HashMap;
import java.util.Map;

/**
 * 166. Fraction to Recurring Decimal.
 */
public class FractionToDecimal {

    /**
     * Use a hash table that maps from the remainder to its position of the fractional part.
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0 || denominator == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        //sign, only one is negative
        if (numerator < 0 ^ denominator < 0) {
            sb.append("-");
        }

        long num = Math.abs(Long.valueOf(numerator));
        long de = Math.abs(Long.valueOf(denominator));

        long quotient = num / de;
        sb.append(quotient);

        long remainder = num % de;
        if (remainder == 0) {
            return sb.toString();
        } else {
            sb.append(".");
        }

        //key is remainder, value is index
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                int index = map.get(remainder);
                sb.insert(index, "(");
                sb.append(")");
                break;
            } else {
                map.put(remainder, sb.length());
                remainder *= 10;
                sb.append(remainder / de);
                remainder = remainder % de;
            }
        }

        return sb.toString();
    }
}
