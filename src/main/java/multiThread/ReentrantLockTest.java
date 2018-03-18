package multiThread;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    static class Runner {

        private int count = 0;
        private Lock lock = new ReentrantLock();
        private Condition cond = lock.newCondition();

        private void increment() {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        }

        public void firstThread() throws InterruptedException {
            lock.lock();

            System.out.println("Waiting ... ");

            cond.await();

            System.out.println("Woken up!");

            try {
                increment();
                System.out.println("1st thread count is " + count);
            } finally {
                lock.unlock();
            }

        }

        public void secondThread() throws InterruptedException {
            Thread.sleep(1000);
            lock.lock();

            System.out.println("Press the return key");
            new Scanner(System.in).nextLine();
            System.out.println("Got the return key");

            cond.signal(); // still have to unlock

            try {
                increment();
                System.out.println("2nd thread count is " + count);
            } finally {
                lock.unlock(); //不加这句就有dead lock
            }
        }

        public void finished() {
            System.out.println("Count is: " + count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Runner runner = new Runner();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finished();
    }
}
