package multiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 两个锁，可以有两个thread，一个访问list1, 一个访问list2.  不能有两个thread同时访问list1。程序2s结束
 * synchronized block
 *
 * 一个锁的话，synchronized method, 只能有一个thread 访问list1或list2. 程序5s结束
 */
public class MultiLocks {

    private Random random = new Random();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void stageOne() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock1) {
            list1.add(random.nextInt(100));
        }
    }

    public void stageTwo() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock2) {
            list2.add(random.nextInt(100));
        }
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            //two locks 程序2s多结束
            stageOne();
            stageTwo();

            //one lock 程序5s结束
//            stageOneOneLock();
//            stageTwoOneLock();
        }
    }

    public static void main(String[] args) {
        MultiLocks obj = new MultiLocks();
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.process();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.process();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.err.println("Take: " + (end - start));
        System.err.println("List1: " + obj.list1.size() + ", List2: " + obj.list2.size());
    }

    //------------------- 一个锁对比 ---------------------------------------------------------
    public synchronized void stageOneOneLock() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list1.add(random.nextInt(100));
    }

    public synchronized void stageTwoOneLock() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list2.add(random.nextInt(100));
    }
}
