package Interview.twosigma.fridge;

public class HungryRoommate implements Runnable {

    Fridge fridge;
    String preference;

    public HungryRoommate(String preference, Fridge fridge) {
        this.preference = preference;
        this.fridge = fridge;
    }

    @Override
    public void run() {
        while (true) {
            eat();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void eat() {

        FridgeItem toEat = fridge.getFridgeItem(preference);

        if (toEat.semaphore.tryAcquire()) {
            if (toEat.eat()) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                toEat.semaphore.release();
                return;
            }

            toEat.semaphore.release();
        } else {
            for (String key : fridge.map.keySet()) {
                if (!key.equals(preference)) {
                    toEat = fridge.getFridgeItem(key);

                    if (toEat.semaphore.tryAcquire()) {
                        if (toEat.eat()) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            toEat.semaphore.release();
                            break;
                        }
                        toEat.semaphore.release();
                    }
                }
            }
        }

    }
}
