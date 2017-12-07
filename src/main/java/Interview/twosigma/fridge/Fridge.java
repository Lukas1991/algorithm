package Interview.twosigma.fridge;

import java.util.Map;

public class Fridge {

    Map<String, FridgeItem> map;

    public Fridge(Map<String, FridgeItem> map) {
        this.map = map;
        new Thread(new TestClass(this)).start();
    }

    FridgeItem getFridgeItem(String preference) {
        return map.get(preference);
    }

    public void print() {
        System.out.println(map.get("apple").toString());
        System.out.println(map.get("egg").toString());
        System.out.println(map.get("milk").toString());

        for (FridgeItem q : map.values()) {
            if (q.currentCount > q.maxCount || q.currentCount < 0) {
                System.out.println("Check Fail");
                break;
            }
        }
        System.out.println("------------------------------");
    }

    class TestClass implements Runnable {
        Fridge fridge;

        public TestClass(Fridge fridge) {
            this.fridge = fridge;
        }

        @Override
        public void run() {
            while (true) {
                fridge.print();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
