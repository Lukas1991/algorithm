package twosigma;

import dp.Stock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StockTest {

    Stock stock = new Stock();

    @Test
    public void testEmpty() {
        int[] prices = {};
        assertEquals(0, stock.maxProfit3Best(prices));
    }

    @Test
    public void testNotBuy() {
        int[] prices = {3,2,1};
        assertEquals(0, stock.maxProfit3Best(prices));
    }

    @Test
    public void testOnce1() {
        int[] prices = {1, 2, 3, 4, 5};
        assertEquals(4, stock.maxProfit3Best(prices));  //5-1
    }

    @Test
    public void testOnce2() {
        int[] prices = {5, 4, 1, 2, 3};
        assertEquals(2, stock.maxProfit3Best(prices));  //3-2
    }


    //Increasing
    @Test
    public void testTwice1() {
        int[] prices = {2, 3, 4, 0, 1};
        assertEquals(3, stock.maxProfit3Best(prices));  //(4-2) + (1-0)
    }

    //先降再增
    @Test
    public void testTwice() {
        int[] prices = {2, 1, 2, 0, 1};
        assertEquals(2, stock.maxProfit3Best(prices));  //(2-1) + (1-0)
    }
}
