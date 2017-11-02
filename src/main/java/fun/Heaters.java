package fun;

import java.util.Arrays;

public class Heaters {

    /**
     * we can ignore houses on heaters, and ~ (bitwise compliment)
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int res = 0;
        for (int house : houses) {
            //This method returns index of the search key, if it is contained in the array,
            //else it returns (-(insertion point) - 1)
            int index = Arrays.binarySearch(heaters, house);

            if (index < 0) {
                //The following insertion point is the same as index = ~index;
                int insert = - index - 1;
                int disToLeft = (insert - 1 >= 0) ? house - heaters[insert - 1] : Integer.MAX_VALUE;
                int disToRight = insert < heaters.length ? heaters[insert] - house : Integer.MAX_VALUE;

                int minDis = Math.min(disToLeft, disToRight);
                res = Math.max(res, minDis);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] houses = {1,2,3,4,5};
        int[] heaters = {1,4};
        //System.err.println(Arrays.binarySearch(heaters, 1));   --> 0
        //System.err.println(Arrays.binarySearch(heaters, 4));   --> 1

        System.err.println(Arrays.binarySearch(heaters, 2));     //--> -2
        System.err.println(~Arrays.binarySearch(heaters, 2));    //--> 1
        System.err.println(getInsertPoint(Arrays.binarySearch(heaters, 2)));  //--> 1

        System.err.println(Arrays.binarySearch(heaters, 3));
        System.err.println(~Arrays.binarySearch(heaters, 3));
        System.err.println(getInsertPoint(Arrays.binarySearch(heaters, 3)));

        System.err.println(Arrays.binarySearch(heaters, 5));    //--> -3
        System.err.println(~Arrays.binarySearch(heaters, 5));    //--> 2
        System.err.println(getInsertPoint(Arrays.binarySearch(heaters, 5)));    //--> 2
    }

    private static int getInsertPoint(int index) {
        return -index - 1;
    }
}
