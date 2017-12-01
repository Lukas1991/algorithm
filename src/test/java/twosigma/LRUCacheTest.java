package twosigma;

import Interview.twosigma.LRUCache;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LRUCacheTest {

    @Test
    public void testCapacity2() {
        LRUCache cache = new LRUCache(2);
        assertEquals(-1, cache.get(1));
        cache.set(1, 1);
        cache.set(2, 2);
        assertEquals(1, cache.get(1)); //1-2
        cache.set(3, 3); //evict 2, 3-1

        assertEquals(-1, cache.get(2));
        cache.set(1, 10); //1->3
        cache.set(4,4); //4->1, evict 3

        assertEquals(-1, cache.get(3));
        assertEquals(10, cache.get(1));
    }

    @Test
    public void testCapacity1() {
        LRUCache cache = new LRUCache(1);
        assertEquals(-1, cache.get(1));

        cache.set(1, 1);
        assertEquals(1, cache.get(1));

        cache.set(2, 2); //evict 1
        assertEquals(-1, cache.get(1));

        cache.set(3, 3); //evict 2
        assertEquals(-1, cache.get(2));
    }

    @Test
    public void testCapacity0() {
        LRUCache cache = new LRUCache(0);
        assertEquals(-1, cache.get(1));

        cache.set(1, 1);
        assertEquals(-1, cache.get(1));
    }
}
