package multiThread;

import java.util.LinkedList;
import java.util.Random;

public class ProducerConsumerTestWaitNotify2 {

    static class Processor {

        private LinkedList<Integer> list = new LinkedList<>();
        private final int LIMIT = 10;

        private Object lock = new Object(); //lock for the list

        public void produce() throws InterruptedException {
            int val = 0;

            while (true) {
                synchronized (lock) {

                    while (list.size() == LIMIT) {
                        lock.wait();
                    }

                    list.add(val++);
                    lock.notify();
                }
            }
        }

        public void consume() throws InterruptedException {

            Random random = new Random();

            while (true) {
                synchronized (lock) {

                    while (list.size() == 0) {
                        lock.wait();
                    }

                    int val = list.removeFirst();
                    System.err.println("List size is: " + list.size() + ", removed value : " + val);
                    lock.notify();
                }

                Thread.sleep(random.nextInt(1000));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
