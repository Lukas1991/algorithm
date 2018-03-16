package multiThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A synchronization aid that allows one or more threads to wait until a set of operations being performed in other threads completes.
 * Another typical usage would be to divide a problem into N parts, wait all sub-parts are completed.
 */
public class CountDownLatchTest {

    static class ProcessorThread implements Runnable {
        private int id;

        private CountDownLatch latch;

        public ProcessorThread(CountDownLatch latch, int id) {
            this.latch = latch;
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Starting: ");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(id + " latch getCount: " + latch.getCount());
            latch.countDown();
            System.out.println(id + " latch getCount: " + latch.getCount());
        }
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executor.submit(new ProcessorThread(latch, i));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Completed.");
    }
}
