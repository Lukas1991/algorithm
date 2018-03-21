package Interview.twosigma.fridge;

public class NiceRoomate implements Runnable {

    Fridge fridge;

    public NiceRoomate(Fridge fridge) {
        this.fridge = fridge;
    }

    @Override
    public void run() {
        while (true) {
            restock();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void restock() {
        for (FridgeItem q : fridge.map.values()) {
            FridgeItem toStock = q;

            if (q.semaphore.tryAcquire()) {

                if (toStock.restorck()) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    q.semaphore.release();
                    break;
                } else {
                    q.semaphore.release();
                }
            }

        }
    }
}
