package junit.framework;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by KGuly on 28.12.2016.
 */
public class ComparisonCompactorCleanCodeTest {

    @Test
    public void testMessage() {
        String failure = new ComparisonCompactorCleanCode(0, "b", "c").compact("a");
        assertTrue("a expected:<[b]> but was:<[c]>".equals(failure));
    }

    @Test
    public void testStartSame() {
        String failure = new ComparisonCompactorCleanCode(1, "ba", "bc").compact(null);
        assertEquals("expected:<b[a]> but was:<b[c]>", failure);
    }

    @Test
    public void testEndSame() {
        String failure = new ComparisonCompactorCleanCode(1, "ab", "cb").compact(null);
        assertEquals("expected:<[a]b> but was:<[c]b>", failure);
    }

    @Test
    public void testSame() {
        String failure = new ComparisonCompactorCleanCode(1, "ab", "ab").compact(null);
        assertEquals("expected:<ab> but was:<ab>", failure);
    }

    @Test
    public void testNoContextStartAndEndSame() {
        String failure = new ComparisonCompactorCleanCode(0, "abc", "adc").compact(null);
        assertEquals("expected:<...[b]...> but was:<...[d]...>", failure);
    }

    @Test
    public void testStartAndEndContext() {
        String failure = new ComparisonCompactorCleanCode(1, "abc", "adc").compact(null);
        assertEquals("expected:<a[b]c> but was:<a[d]c>", failure);
    }

    @Test
    public void testStartAndEndContextWithEllipses() {
        String failure = new ComparisonCompactorCleanCode(1, "abcde", "abfde").compact(null);
        assertEquals("expected:<...b[c]d...> but was:<...b[f]d...>", failure);
    }

    @Test
    public void testComparisonErrorStartSameComplete() {
        String failure = new ComparisonCompactorCleanCode(2, "ab", "abc").compact(null);
        assertEquals("expected:<ab[]> but was:<ab[c]>", failure);
    }

    @Test
    public void testComparisonErrorEndSameComplete() {
        String failure = new ComparisonCompactorCleanCode(0, "bc", "abc").compact(null);
        assertEquals("expected:<[]...> but was:<[a]...>", failure);
    }

    @Test
    public void testComparisonErrorEndSameCompleteContext() {
        String failure = new ComparisonCompactorCleanCode(2, "bc", "abc").compact(null);
        assertEquals("expected:<[]bc> but was:<[a]bc>", failure);
    }

    @Test
    public void testComparisonErrorOverlappingMatches() {
        String failure = new ComparisonCompactorCleanCode(0, "abc", "abbc").compact(null);
        assertEquals("expected:<...[]...> but was:<...[b]...>", failure);
    }

    @Test
    public void testComparisonErrorOverlappingMatchesContext() {
        String failure = new ComparisonCompactorCleanCode(2, "abc", "abbc").compact(null);
        assertEquals("expected:<ab[]c> but was:<ab[b]c>", failure);
    }

    @Test
    public void testComparisonErrorOverlappingMatches2() {
        String failure = new ComparisonCompactorCleanCode(0, "abcdde", "abcde").compact(null);
        assertEquals("expected:<...[d]...> but was:<...[]...>", failure);
    }

    @Test
    public void testComparisonErrorOverlappingMatches2Context() {
        String failure = new ComparisonCompactorCleanCode(2, "abcdde", "abcde").compact(null);
        assertEquals("expected:<...cd[d]e> but was:<...cd[]e>", failure);
    }

    @Test
    public void testComparisonErrorWithActualNull() {
        String failure = new ComparisonCompactorCleanCode(0, "a", null).compact(null);
        assertEquals("expected:<a> but was:<null>", failure);
    }

    @Test
    public void testComparisonErrorWithActualNullContext() {
        String failure = new ComparisonCompactorCleanCode(2, "a", null).compact(null);
        assertEquals("expected:<a> but was:<null>", failure);
    }

    @Test
    public void testComparisonErrorWithExpectedNull() {
        String failure = new ComparisonCompactorCleanCode(0,null, "a").compact(null);
        assertEquals("expected:<null> but was:<a>", failure);
    }

    @Test
    public void testComparisonErrorWithExpectedNullContext() {
        String failure = new ComparisonCompactorCleanCode(2,null, "a").compact(null);
        assertEquals("expected:<null> but was:<a>", failure);
    }

    @Test
    public void testBug609972() {
        String failure = new ComparisonCompactorCleanCode(10,"S&P500", "0").compact(null);
        assertEquals("expected:<[S&P50]0> but was:<[]0>", failure);
    }
}
