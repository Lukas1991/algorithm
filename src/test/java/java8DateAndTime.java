import java.time.Duration;
import org.junit.Test;

public class java8DateAndTime {

    @Test
    public void testDuration() {
        Duration d1 = Duration.ZERO;
        d1 = d1.plusDays(2).plusMinutes(9).plusSeconds(5);

        System.err.println(d1.toString());
        System.err.println(d1.getSeconds());

        Duration d2 = Duration.parse("PT1M");  //1 minute
        System.err.println(d2.getSeconds());

        Duration d3 = Duration.ofSeconds(300);  //300 seconds
        System.err.println(d3.toString());
    }
}
