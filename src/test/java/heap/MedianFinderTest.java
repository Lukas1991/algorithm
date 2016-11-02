package heap;

import org.junit.Assert;
import org.junit.Test;


public class MedianFinderTest {

    private MedianFinder medianFinder = new MedianFinder();

    @Test
    public void test() {
        int[] numbers = {2,3,4};

        for (int num : numbers) {
            medianFinder.addNum(num);
        }

        double result = medianFinder.findMedian();
        Assert.assertTrue(result == 3.0);
    }
}
