package multiThread;

import java.util.Scanner;

public class ProducerConsumerTestWaitNotify {

    static class Processor {

        public void produce() throws InterruptedException {
            synchronized (this) {  //lock of processor
                System.out.println("Producer thread running ...");
                wait(); // hand over the lock this block acquire, let this block lose the control of the lock

                //this thread will resume when this block acquire the lock again (other thread calls notify())
                System.out.println("Resumed.");
            }
        }

        public void consume() throws InterruptedException {
            Scanner scanner = new Scanner(System.in);

            Thread.sleep(2000);

            synchronized (this) { // lock on the same as above - the processor lock
                System.out.println("Waiting for return key.");
                scanner.nextLine();
                System.out.println("Return key pressed.");
                notify();
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
