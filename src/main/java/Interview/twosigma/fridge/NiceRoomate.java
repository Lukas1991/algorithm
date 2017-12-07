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

            if (toStock.currentCount < toStock.maxCount) {

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                toStock.restorck();
                break;
            }
        }
    }
}
