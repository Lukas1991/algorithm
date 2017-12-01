package Interview.twosigma;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class FridgeWithLock {

	Map<String, QueueWrapper> map = new HashMap<>();

	Queue<Integer> apples = new LinkedList<>();
	Queue<Integer> bananas = new LinkedList<>();
	Queue<Integer> pears = new LinkedList<>();

	ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	ReadLock readLock = readWriteLock.readLock();
	WriteLock writeLock = readWriteLock.writeLock();

	public FridgeWithLock() {
		map.put("apple", new QueueWrapper(apples, "apple", 5));
		map.put("banana", new QueueWrapper(bananas, "banana", 3));
		map.put("pear", new QueueWrapper(pears, "pear", 2));
	}

	public synchronized void put(QueueWrapper wrapper, int item) {

		Queue<Integer> q = wrapper.queue;
		int capacity = wrapper.max;
		while (q.size() == capacity) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		q.add(item);
		notifyAll();
	}

	public synchronized int take(QueueWrapper wrapper, boolean isPreferred) {

		Queue<Integer> q = wrapper.queue;

		if (isPreferred && q.isEmpty()) {
			return -1;
		}

		while (q.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		int item = q.poll();
		notifyAll();
		return item;
	}

	public static void main(String[] args) {
		FridgeWithLock fridge = new FridgeWithLock();

		new Thread(new FridgeConsumer2("apple", fridge)).start();
		new Thread(new FridgeConsumer2("banana", fridge)).start();
		new Thread(new FridgeConsumer2("pear", fridge)).start();

		new Thread(new FridgeProducer2("p1", fridge)).start();
		new Thread(new FridgeProducer2("p2", fridge)).start();
	}
}

class FridgeConsumer2 implements Runnable {
	String preference;
	FridgeWithLock fridge;

	public FridgeConsumer2(String preference, FridgeWithLock fridge) {
		this.preference = preference;
		this.fridge = fridge;
	}

	@Override
	public void run() {
		while (true) {
			consume();
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

	}

	private void consume() {

		int item = fridge.take(fridge.map.get(preference), true);
		if (item == -1) {

			for (Map.Entry<String, QueueWrapper> entry : fridge.map.entrySet()) {
				if (entry.getKey().equals(preference)) {
					continue;
				}

				item = fridge.take(entry.getValue(), false);
				System.out.println("Eating " + item);
				break;
			}
		} else {
			System.out.println("Eating preference " + item);
		}
	}
}

class FridgeProducer2 implements Runnable {
	String producerName;
	FridgeWithLock fridge;
	Random rand = new Random();

	public FridgeProducer2(String producerName, FridgeWithLock fridge) {
		this.producerName = producerName;
		this.fridge = fridge;
	}

	@Override
	public void run() {
		while (true) {
			produce();

			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void produce() {
		for (Map.Entry<String, QueueWrapper> entry : fridge.map.entrySet()) {

			int item = rand.nextInt(100);
			fridge.put(entry.getValue(), item);
			System.out.println("Put " + item);
		}
	}
}