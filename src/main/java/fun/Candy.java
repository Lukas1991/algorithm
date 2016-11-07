package fun;

/**
 * There are N children standing in a line. Each child is assigned a rating value.

 You are giving candies to these children subjected to the following requirements:

 Each child must have at least one candy.
 Children with a higher rating get more candies than their neighbors.
 What is the minimum candies you must give?
 */
public class Candy {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int size = ratings.length;
        int[] arr = new int[size];

        //left to right, if ascending, plus one; if not ascending, assign 1
        arr[0]=1;
        for(int i=1; i<size; i++){
            if(ratings[i] > ratings[i-1]){
                arr[i]=arr[i-1] + 1;
            }else{
                arr[i]=1;
            }
        }


        //right to left, if ascending, Max of current value and plus one;
        //if not ascending, use the current value
        int total = arr[size-1];
        for(int i=size-2; i>=0; i--){
            if(ratings[i] > ratings[i+1]){
                arr[i] = Math.max(arr[i], arr[i+1]+1);
            }else{
                //keep the old arr[i]
            }
            total += arr[i];
        }
        return total;
    }
}
