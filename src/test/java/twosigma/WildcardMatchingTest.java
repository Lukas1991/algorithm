package twosigma;

import dp.WildcardMatching;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WildcardMatchingTest {

    WildcardMatching wildcardMatching = new WildcardMatching();

    @Test
    public void testIsMatchBothEmpty() {
        assertThat(wildcardMatching.isMatch("", ""), is(true));
    }

    @Test
    public void testIsMatchEmptyS() {
        final String s = "";
        assertTrue(wildcardMatching.isMatch(s, "*"));
        assertTrue(wildcardMatching.isMatch(s, "**"));

        assertFalse(wildcardMatching.isMatch(s, "a"));
        assertFalse(wildcardMatching.isMatch(s, "?"));
        assertFalse(wildcardMatching.isMatch(s, "*?"));
    }

    @Test
    public void testIsMatchEmptyP() {
        final String p = "";
        assertFalse(wildcardMatching.isMatch("a", p));
    }

    @Test
    public void testIsMatchExact() {
        assertTrue(wildcardMatching.isMatch("abc", "abc"));
        assertFalse(wildcardMatching.isMatch("aba", "abc"));
    }

    @Test
    public void testIsMatchWithQuestionMark() {
        String p = "?";
        assertThat(wildcardMatching.isMatch("a", p), is(true));
        assertThat(wildcardMatching.isMatch("ab", p), is(false));

        p = "?a??b?";
        assertThat(wildcardMatching.isMatch("xayzbz", p), is(true));
        assertThat(wildcardMatching.isMatch("aybz", p), is(false));
        assertThat(wildcardMatching.isMatch("xabz", p), is(false));
        assertThat(wildcardMatching.isMatch("xayb", p), is(false));

        p = "a??b";
        assertThat(wildcardMatching.isMatch("axyb", p), is(true));
        assertThat(wildcardMatching.isMatch("axb", p), is(false));
    }

    @Test
    public void testIsMatchWithStar() {
        String p = "*";
        assertThat(wildcardMatching.isMatch("a", p), is(true));
        assertThat(wildcardMatching.isMatch("ab", p), is(true));

        p = "**";
        assertThat(wildcardMatching.isMatch("a", p), is(true));
        assertThat(wildcardMatching.isMatch("ab", p), is(true));

        p = "a*b";
        assertThat(wildcardMatching.isMatch("ab", p), is(true));
        assertThat(wildcardMatching.isMatch("acb", p), is(true));
        assertThat(wildcardMatching.isMatch("abcc", p), is(false));
    }

    @Test
    public void testIsMatchPreStar() {
        String p = "b*c";
        assertThat(wildcardMatching.isMatch("bcdc", p), is(true));
    }

    @Test
    public void testIsMatchPWithExtraStar() {
        String p = "a*b*";
        assertThat(wildcardMatching.isMatch("ab", p), is(true));
        assertThat(wildcardMatching.isMatch("axb", p), is(true));
    }

    @Test
    public void testIsMatchPWithExtraChar() {
        String p = "a*bc";
        assertThat(wildcardMatching.isMatch("abc", p), is(true));
        assertThat(wildcardMatching.isMatch("axybc", p), is(true));

        assertThat(wildcardMatching.isMatch("a", p), is(false));
        assertThat(wildcardMatching.isMatch("ax", p), is(false));
        assertThat(wildcardMatching.isMatch("axb", p), is(false));
        assertThat(wildcardMatching.isMatch("axc", p), is(false));
    }

    @Test
    public void testIsMatchCombo() {
        assertThat(wildcardMatching.isMatch("abc", "?*"), is(true));
        assertThat(wildcardMatching.isMatch("abc", "*?"), is(true));
        assertThat(wildcardMatching.isMatch("abc", "a?*?"), is(true));
        assertThat(wildcardMatching.isMatch("ac", "a?*?"), is(false));

        assertThat(wildcardMatching.isMatch("abc", "*?*"), is(true));
        assertThat(wildcardMatching.isMatch("ba", "*?*a"), is(true));
        assertThat(wildcardMatching.isMatch("ba", "*?*a"), is(true));
    }

    @Test
    public void testIsMatchLargeString() {
        String s = "aaaaaaaaa";
        String p = "*aa";
        wildcardMatching.isMatch(s, p);

    }
}
