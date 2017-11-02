package dp;

/**
 * http://www.crazyforcode.com/min-number-platforms-required-railway-station/
 * At a bus-station, you have time-table for buses arrival and departure. You need to find the minimum number of platforms so that all the buses can be placed as per their schedule.

 For example consider the above example.

 arr[] = {9:00, 9:40, 9:50, 11:00, 15:00, 18:00}
 dep[] = {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}

 All events sorted by time.
 */
public class MinPlatforms {

    public static int findPlatform(int arr[], int dep[], int n) {
        int plat_needed = 1, result = 1;
        int i = 1, j=0;

        while (i < n && j < n) {
            if (dep[j] < arr[i]) {
                //decrements count of platforms needed
                plat_needed--;
                j++;
            } else {
                plat_needed++;
                i++;
                if (plat_needed > result) {
                    result = plat_needed;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int arr[] = {900, 940, 950, 1100, 1500, 1800};
        int dep[] = {910, 1200, 1120, 1130, 1900, 2000};

        int result = findPlatform(arr, dep, arr.length);
        System.out.println(result);
    }
}
