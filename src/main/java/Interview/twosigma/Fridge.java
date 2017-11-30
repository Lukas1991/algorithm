package Interview.twosigma;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Fridge {
    Map<String, QueueWrapper> map = new HashMap<>();

    Queue<Integer> apples = new LinkedList<>();
    Queue<Integer> bananas = new LinkedList<>();
    Queue<Integer> pears = new LinkedList<>();

    public Fridge() {
        map.put("apple", new QueueWrapper(apples, "apple", 5));
        map.put("banana", new QueueWrapper(bananas, "banana", 3));
        map.put("pear", new QueueWrapper(pears, "pear", 2));
    }

    public static void main(String[] args) {
        Fridge fridge = new Fridge();

        new Thread(new FridgeConsumer("apple", fridge)).start();
        new Thread(new FridgeConsumer("banana", fridge)).start();
        new Thread(new FridgeConsumer("pear", fridge)).start();

        new Thread(new FridgeProducer("p1", fridge)).start();
        new Thread(new FridgeProducer("p2", fridge)).start();
    }
}

class QueueWrapper {
    Queue<Integer> queue = new LinkedList<>();
    String name;
    int max;

    public QueueWrapper(Queue<Integer> queue, String name, int max) {
        this.queue = queue;
        this.name = name;
        this.max = max;
    }
}

class FridgeConsumer implements Runnable {
    String preference;
    Fridge fridge;

    public FridgeConsumer(String preference, Fridge fridge) {
        this.preference = preference;
        this.fridge = fridge;
    }

    @Override
    public void run() {
        while (true) {
            consume();

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void consume() {
        Queue<Integer> preferenceQueue = fridge.map.get(preference).queue;
        if (!preferenceQueue.isEmpty()) {
            preferenceQueue.poll();
            System.err.println("consumer: " + preference + " eat " + preference);
        } else {

            for (Map.Entry<String, QueueWrapper> entry : fridge.map.entrySet()) {
                if (entry.getKey().equals(preference)) {
                    continue;
                }

                Queue<Integer> queue = entry.getValue().queue;
                if (!queue.isEmpty()) {
                    queue.poll();
                    System.err.println("consumer: " + preference + " eat " + entry.getKey());
                    break;
                }

            }
        }
    }
}

class FridgeProducer implements Runnable {
    String producerName;
    Fridge fridge;

    public FridgeProducer(String producerName, Fridge fridge) {
        this.producerName = producerName;
        this.fridge = fridge;
    }

    @Override
    public void run() {
        while (true) {
            produce();

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void produce() {
        for (Map.Entry<String, QueueWrapper> entry : fridge.map.entrySet()) {
            Queue<Integer> queue = entry.getValue().queue;

            if (queue.size() < entry.getValue().max) {
                //buy
                queue.offer(1);
                System.err.println("producer: " +  producerName + " buy " + entry.getKey());
            }
        }
    }
}