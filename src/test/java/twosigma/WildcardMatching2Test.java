package twosigma;

import Interview.twosigma.WildcardMatching2;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WildcardMatching2Test {
	
	WildcardMatching2 matching = new WildcardMatching2();
	
	@Test
	public void test() {
		assertTrue(matching.isMatchDP("A212B", "A?1*B"));
		assertFalse(matching.isMatchDP("A21B", "A?1*B"));
		assertTrue(matching.isMatchDP("A21BB", "A?1*B"));
		assertTrue(matching.isMatchDP("A21XYZB", "A?1*B"));
	}

	@Test
	public void testEndsWithStar() {
		assertTrue(matching.isMatchDP("A212B", "A?1*"));
		assertTrue(matching.isMatchDP("A21B", "A?1*"));
		assertTrue(matching.isMatchDP("A21BB", "*"));
		assertTrue(matching.isMatchDP("21XYZB", "?*"));
	}
	
	@Test
	public void testStartsWithStar() {
		assertTrue(matching.isMatchDP("XA12", "*A?2"));
		assertTrue(matching.isMatchDP("XYZA12", "*A?2"));
		assertFalse(matching.isMatchDP("A12", "*A?2"));
		assertTrue(matching.isMatchDP("AAA12", "*A?2"));
	}
	
	@Test
	public void testMultipleStar() {		
		assertTrue(matching.isMatchDP("1B", "1*"));
		assertTrue(matching.isMatchDP("1BC", "1**"));
		assertTrue(matching.isMatchDP("XY1BC", "**1**"));
		assertTrue(matching.isMatchDP("XY1BC", "**?**"));
		assertFalse(matching.isMatchDP("1B", "1**"));
	}
	
	@Test
    public void testIsMatchLargeString() {
        String s = "AAAAAAAA";
        String p = "*AA";
        assertTrue(matching.isMatchDP("AAAAAAAA", "*AA"));
    }
	
	@Test
	public void testEmpty() {
		assertTrue(matching.isMatchDP("", ""));
	}
	
	@Test
	public void testInvalidChar() {
		assertFalse(matching.isMatchDP("a", "*"));
		assertFalse(matching.isMatchDP("a", "?"));
	}
}
