package SortAndSearch;

public class GuessNumber {

    public static void main(String[] args) {

    }

    /**
     * Note be careful about the big input: low = 1702766719, high = 2126753390 Then (high+low) overflows Integer limit in the first
     * place.
     */
    public int guessNumber(int n) {
        return guessNumber(1, n);
    }

    public int guessNumber(int low, int high) {
        Long midLong = (new Long(high) + new Long(low)) / 2;
        int mid = midLong.intValue();

        //Another way to avoid integer overflows
        //int mid = low + (high - low)/2;

        if (guess(mid) == 0) {
            return mid;
        } else if (guess(mid) == -1) {
            return guessNumber(low, mid - 1);
        } else {
            return guessNumber(mid + 1, high);
        }
    }

    //mock guess API
    //return -1 if my number is lower, 1 if my number is higher, otherwise return 0
    private int guess(int n) {
        return 0;
    }
}
