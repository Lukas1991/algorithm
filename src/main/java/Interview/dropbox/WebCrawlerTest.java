package Interview.dropbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * http://scrumbucket.org/tutorials/neo4j-site-crawler/part-2-create-multi-threaded-crawl-manager/
 * https://github.com/yohanakh/java-web-crawler
 */
public class WebCrawlerTest extends Thread {
    //count how many threads are running(not waiting)
    private static AtomicLong counter;

    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    private static Map<String, Boolean> visited = new ConcurrentHashMap<>();
    //private static HashMap<String, Boolean> visited = new HashMap<>();

    private static List<String> results = Collections.synchronizedList(new ArrayList<>());  //!!!!!

    public static List<String> getResults() {
        return results;
    }

    public static void init(String url, int threadNum) {
        counter = new AtomicLong(threadNum);
        try {
            queue.put(url);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Long getCounter() {
        return counter.get();
    }

    @Override
    public void run() {
        try {
            while (true) {
                counter.decrementAndGet();
                String url = queue.take();
                counter.incrementAndGet();

//                String domain = "";
//                try {
//                    URL netUrl = new URL(url);
//                    domain = netUrl.getHost();
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//              domain.endsWith("wikipedia.org")

                if (!visited.containsKey(url)) {
                    bfs(url);
                }

            }
        } catch (InterruptedException e) {
            System.err.println("interrupted counter is : " + counter.get());
            Thread.interrupted();
        } catch (Exception e) {
        }
    }

    private void bfs(String url) throws InterruptedException {
        visited.put(url, true);
        results.add(url);

        List<String> urls = parse(url);
        for (String u : urls) {
            if (!visited.containsKey(u) && u.endsWith("wikipedia.org")) {
                queue.put(u);
            }
        }
    }

    private static List<String> parse(String url) {
        Random random = new Random();
        List<String> list = new ArrayList<>();
        String domain = "wikipedia.org";

        for (int i = 0; i < 3; i++) {
            list.add(random.nextInt(100) + "-" + domain);
        }
        return list;
    }

    public static void test1() {
        int threadNum = 5;
        WebCrawlerTest.init("wikipedia.org", threadNum);
        WebCrawlerTest[] pool = new WebCrawlerTest[threadNum];

        for (int i = 0; i < threadNum; i++) {
            pool[i] = new WebCrawlerTest();
            pool[i].start();
        }

        while (WebCrawlerTest.getCounter() > 0) {
            //
        }

        for (int i = 0; i < threadNum; i++) {
            pool[i].stop();
        }
        System.out.println("All tasks stoped.");
        System.out.println(WebCrawlerTest.getResults().size());
    }

    public static void test2() {
        int threadNum = 5;
        WebCrawlerTest.init("wikipedia.org", threadNum);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < threadNum; i++) {
            executor.submit(new WebCrawlerTest());
        }

        System.out.println("All tasks submitted.");

        while (WebCrawlerTest.getCounter() > 0) {
            //
        }

        //all threads are waiting.
        System.out.println("All tasks waiting.");

        try {
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("shut donw now.");
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("All tasks completed." + executor.isTerminated());

        System.out.println(WebCrawlerTest.getResults().size());
        //System.out.println(WebCrawlerTest.getResults());
        System.out.println(WebCrawlerTest.queue.size());
        System.out.println(WebCrawlerTest.visited.size());
        System.out.println("Counter: " + WebCrawlerTest.getCounter());
    }

    public static void main(String[] args) throws InterruptedException {
        test1();
        //test2();
    }
}
