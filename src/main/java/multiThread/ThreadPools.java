package multiThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadPools {

    static class ProcessorThread implements Runnable {
        //Test synchronizedList
        static List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        //static List<Integer> list = new ArrayList<>();

        private int id;

        public ProcessorThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Starting: " + id);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(1);
            System.out.println("Completed: " + id);
        }
    }

    public static void submitTest() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executor.submit(new ProcessorThread(i));
        }

        System.out.println("All tasks submiteted.");

        executor.shutdown();

        try {
            executor.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed.");
        System.out.println(ProcessorThread.list.toString());
    }

    //thread return a value
    public static void callableTest() {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                Random random = new Random();
                int duration = random.nextInt(4000);

                if (duration > 2000) {
                    throw new IOException("Sleeping for too long.");
                }

                System.out.println("Starting ...");

                Thread.sleep(duration);

                System.out.println("Finished");

                return duration;
            }
        });

        executor.shutdown();

        try {
            System.out.println("Result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {

            //future.get() will throw ExecutionException
            IOException exception = (IOException) e.getCause();
            System.out.println(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        submitTest();

        //System.out.println("Callable Test ...");
        //callableTest();
    }
}

