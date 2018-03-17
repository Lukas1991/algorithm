package multiThread;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {

    private static final AtomicLong number = new AtomicLong();

    public static long getNumber() {
        return number.getAndIncrement();
    }

}
