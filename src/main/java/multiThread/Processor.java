package multiThread;

import java.util.Scanner;

public class Processor {

    public void produce() throws InterruptedException {
        synchronized (this) {  //lock of processor
            System.out.println("Producer thread running ...");
            wait(); // hand over the lock this block acquire, let this block lose the controle of the lock

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
