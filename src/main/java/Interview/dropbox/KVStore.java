package Interview.dropbox;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Optimistic locking http://www.byteslounge.com/tutorials/jpa-entity-versioning-version-and-optimistic-locking
 *
 * //on write, create a copy from old record, increase version
 * //when commit, check the old version with the version in db.
 */
public class KVStore {

    Map<String, Record> db = new HashMap<>();
    Map<String, Object> locks = new HashMap<>();

    Map<String, LinkedList<Transaction>> updateHistory = new HashMap<>();
    Map<UUID, Transaction> transactionMap = new HashMap<>();

    private static final int maxUpdateRetires = 1;

    public int read(String key) {
        return db.get(key).value;
    }

    public Record write(String key, int value) {
        Record record = db.get(key);
        Record newRecord = new Record(record);

        int oldVersion = record.version;

        //update value and version
        newRecord.value = value;
        newRecord.version++;


        for (int retry = maxUpdateRetires; retry >= 0; retry--) {
            try {
                return commit(newRecord, oldVersion);
            } catch (Exception ex) {
                System.err.println(ex.getMessage() + "retry: " + retry);
            }
        }
        //return commit(newRecord, oldVersion);

        return null;
    }

    Record commit(Record newRecord, int oldVersion) {
        Object lock = locks.get(newRecord.key);

        synchronized (lock) {
            Record oldRecord = db.get(newRecord.key);
            if (oldRecord.version != oldVersion) {
                throw new RuntimeException("version not match");
            }
            db.put(newRecord.key, newRecord);
            System.err.println("updated to " + newRecord.toString());
            return newRecord;
        }
    }

    //-------------用 Transaction, 支持revert--------------------------------
    UUID begin() {
        Transaction t = new Transaction();
        t.transactionId = UUID.randomUUID();
        transactionMap.put(t.transactionId, t);
        return t.transactionId;
    }

    public int read(UUID transactionId, String key) {
        Transaction t = transactionMap.get(transactionId);
        Record record = db.get(key);
        t.key = key;
        t.oldRecord = record;
        return record.value;
    }

    public Record write(UUID transactionId, String key, int value) {
        Transaction t = transactionMap.get(transactionId);
        if (t.oldRecord == null) {
            Record oldRecord = db.get(key);
            t.key = key;
            t.oldRecord = oldRecord;
        }

        Record newRecord = new Record(t.oldRecord);
        //update value and version
        newRecord.value = value;
        newRecord.version++;

        return commit(t);
    }

    Record commit(Transaction transaction) {
        String key = transaction.key;
        Record oldRecord = db.get(key);
        if (oldRecord.version != transaction.oldRecord.version) {
            throw new RuntimeException("version not match");
        }

        db.put(key, transaction.newRecord);
        transaction.commited = true;
        updateHistory.putIfAbsent(key, new LinkedList<>());
        updateHistory.get(key).add(transaction);
        return db.get(key);
    }

    Record revert(UUID transactionId) {
        Transaction t = transactionMap.get(transactionId);
        if (!t.commited) {
            return null;
        }

        db.put(t.key, t.oldRecord);

        LinkedList<Transaction> historys = updateHistory.get(t.key);
        //iterator, find this transction, mark all transactions after this to false
        t.commited = false;
        return db.get(t.key);
    }

    class Transaction {
        UUID transactionId;
        String key;
        Record oldRecord;
        Record newRecord;
        boolean commited = false;
    }

    static class MyRunnable implements Runnable {
        KVStore kvStore;
        String key;

        public MyRunnable(KVStore kvStore, String key) {
            this.kvStore = kvStore;
            this.key = key;
        }

        public void run() {
            System.err.println("Thread " + Thread.currentThread().getId() + " read " + this.key + " : " + kvStore.read(this.key));

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Record updated = kvStore.write(this.key, (int) Thread.currentThread().getId());
            } catch (Exception ex) {
                System.err.println("Thread " + Thread.currentThread().getId() + ex.getMessage());
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        KVStore kvStore = new KVStore();
        kvStore.db.put("a", new Record("a", 1, 1));
        kvStore.db.put("b", new Record("b", 2, 1));
        kvStore.db.put("c", new Record("c", 3, 1));

        kvStore.locks.put("a", new Object());
        kvStore.locks.put("b", new Object());
        kvStore.locks.put("c", new Object());

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executor.submit(new Thread(new MyRunnable(kvStore, "a")));
        }
        for (int i = 0; i < 5; i++) {
            executor.submit(new Thread(new MyRunnable(kvStore, "b")));
        }


        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks finished.");
        System.out.println(kvStore.db.toString());
    }
}

class Record {
    String key;
    int value;
    int version;

    public Record(String key, int value, int version) {
        this.key = key;
        this.value = value;
        this.version = version;
    }

    public Record(Record record) {
        this.key = record.key;
        this.value = record.value;
        this.version = record.version;
    }

    @Override
    public String toString() {
        return "Record{" +
            "key='" + key + '\'' +
            ", value=" + value +
            ", version=" + version +
            '}';
    }
}



