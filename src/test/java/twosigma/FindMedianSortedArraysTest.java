package twosigma;

import array.FindMedianSortedArrays;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindMedianSortedArraysTest {

    FindMedianSortedArrays obj = new FindMedianSortedArrays();

    @Test
    public void testBothEmpty() {
        int[] A = {};
        int[] B = {};
        double median = obj.findMedianSortedArrays(A, B);
        assertEquals(0.0, median, 0.001);
    }

    @Test
    public void testOneArrayEmptyOdd() {
        int[] A = {};
        int[] B = {1};
        double median = obj.findMedianSortedArrays(A, B);
        assertEquals(1.0, median, 0.001);
    }

    @Test
    public void testOneArrayEmptyEven() {
        int[] A = {1,2};
        int[] B = {};
        double median = obj.findMedianSortedArrays(A, B);
        assertEquals(1.5, median, 0.001);
    }

    @Test
    public void testOdd() {
        int[] A = {1,3,7};
        int[] B = {2,4,5,6};
        double median = obj.findMedianSortedArrays(A, B);
        assertEquals(4.0, median, 0.001);
    }

    @Test
    public void testEven() {
        int[] A = {1,3,7,9};
        int[] B = {2,4,5,6};
        double median = obj.findMedianSortedArrays(A, B);
        assertEquals(4.5, median, 0.001);
    }

    @Test
    public void testContainsDuplicateEven() {
        int[] A = {1,4,7};
        int[] B = {2,4,4,6};
        double median = obj.findMedianSortedArrays(A, B);
        assertEquals(4.0, median, 0.001);
    }

    @Test
    public void testContainsDuplicate1() {
        int[] A = {1,3,4,9};
        int[] B = {2,4,4,6};
        double median = obj.findMedianSortedArrays(A, B);
        assertEquals(4.0, median, 0.001);
    }

    @Test
    public void testContainsDuplicate2() {
        int[] A = {1,3,4,9};
        int[] B = {2,4,5,6};
        double median = obj.findMedianSortedArrays(A, B);
        assertEquals(4.0, median, 0.001);
    }
}
