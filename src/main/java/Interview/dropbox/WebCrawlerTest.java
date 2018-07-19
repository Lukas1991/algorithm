package Interview.dropbox;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * http://scrumbucket.org/tutorials/neo4j-site-crawler/part-2-create-multi-threaded-crawl-manager/
 * https://github.com/yohanakh/java-web-crawler
 */
public class WebCrawlerTest extends Thread {
    //count how many threads are running(not waiting)
    private static AtomicLong counter;

    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    //private Map<String, Boolean> visited = new ConcurrentHashMap<>();
    private static HashMap<String, Boolean> visited = new HashMap<>();

    private static List<String> results = Collections.synchronizedList(new ArrayList<>());

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
        while (true) {
            try {
                counter.decrementAndGet();
                String url = queue.take();
                counter.incrementAndGet();

                if (visited.containsKey(url)) {
                    continue;
                }

//                String domain = "";
//                try {
//                    URL netUrl = new URL(url);
//                    domain = netUrl.getHost();
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//              domain.endsWith("wikipedia.org")

                visited.put(url, true);
                results.add(url);

                List<String> urls = parse(url);
                for (String u : urls) {
                    if (!visited.containsKey(u) && u.endsWith("wikipedia.org")) {
                        queue.put(u);
                    }
                }

            } catch (InterruptedException e) {
                Thread.interrupted();
            } catch (Exception e) {


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

    public static void main(String[] args) throws InterruptedException {
        WebCrawlerTest webCrawlerTest = new WebCrawlerTest();

        int threadNum = 5;
        WebCrawlerTest.init("wikipedia.org", threadNum);

//        WebCrawlerTest[] pool = new WebCrawlerTest[threadNum];
//        for (int i = 0; i < threadNum; i++) {
//            pool[i] = new WebCrawlerTest();
//            pool[i].start();
//        }
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < threadNum; i++) {
            executor.submit(() -> webCrawlerTest.run());
        }

        System.out.println("All tasks submitted.");

        while (WebCrawlerTest.getCounter() > 0) {
            //
        }

//        for (int i = 0; i < threadNum; i++) {
//            pool[i].stop();
//        }
//
        //all threads are waiting.
        System.out.println("All tasks waiting.");


        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed." + executor.isTerminated());

        System.out.println(WebCrawlerTest.getResults().size());
//        System.out.println(webCrawlerTest.getResults());
//        System.out.println(webCrawlerTest.queue.size());
//        System.out.println(webCrawlerTest.visited.size());
    }
}
