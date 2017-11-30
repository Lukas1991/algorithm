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
        map.put("banana", new QueueWrapper(bananas, "banana", 5));
        map.put("pear", new QueueWrapper(pears, "pear", 5));
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

            Queue<Integer> preferenceQueue = fridge.map.get(preference).queue;
            if (!preferenceQueue.isEmpty()) {
                preferenceQueue.poll();
            } else {

                for (Map.Entry<String, QueueWrapper> entry : fridge.map.entrySet()) {
                    if (entry.getKey().equals(preference)) {
                        continue;
                    }

                    Queue<Integer> queue = entry.getValue().queue;
                    if (!queue.isEmpty()) {
                        queue.poll();
                        break;
                    }

                }

            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class FridgeProducer implements Runnable {
    Fridge fridge;

    public FridgeProducer(Fridge fridge) {
        this.fridge = fridge;
    }

    @Override
    public void run() {
        while (true) {

            for (Map.Entry<String, QueueWrapper> entry : fridge.map.entrySet()) {
                Queue<Integer> queue = entry.getValue().queue;

                if (queue.size() < entry.getValue().max) {
                    //buy
                    queue.offer(1);
                }
            }

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}