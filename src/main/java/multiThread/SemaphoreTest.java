package multiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    static class Connection {
        private static Connection instance = new Connection();

        private Semaphore semaphore = new Semaphore(10);

        private int connections = 0;

        private Connection() {
        }

        public static Connection getInstance() {
            return instance;
        }

        public void connect() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                doConnect();
            } finally {
                semaphore.release();
            }
        }

        public void doConnect() {
            synchronized (this) {
                connections++;
                System.out.println("Current connections: " + connections);
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                connections--;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
