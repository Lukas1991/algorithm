package multiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadTest {

    private static AtomicInteger counter = new AtomicInteger(0);

    static class MyRunnable implements Runnable {

        public void run() {
            List<Integer> list = get();
            System.err.println("Thread " + Thread.currentThread().getId() + list.toString());
        }

        //try without synchronized
        private synchronized List<Integer> get() {
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i<5; i++) {
                list.add(counter.getAndIncrement());
            }

            return list;
        }
    }


    public static void main(String[] args) {

        Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new Thread(new MyRunnable());
        t1.start();
        t2.start();

        Thread t3 = new Thread(new MyRunnable());
        Thread t4 = new Thread(new MyRunnable());
        t3.start();
        t4.start();
    }

}
