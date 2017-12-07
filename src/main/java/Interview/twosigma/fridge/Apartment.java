package Interview.twosigma.fridge;

import java.util.HashMap;
import java.util.Map;

public class Apartment {

    public static void main(String[] args) {

        Map<String, FridgeItem> map = new HashMap<>();
        map.put("apple", new FridgeItem(5));
        map.put("egg", new FridgeItem(4));
        map.put("milk", new FridgeItem(3));
        Fridge fridge = new Fridge(map);

        HungryRoommate h1 = new HungryRoommate("apple", fridge);
        HungryRoommate h2 = new HungryRoommate("egg", fridge);
        HungryRoommate h3 = new HungryRoommate("milk", fridge);

        NiceRoomate n1 = new NiceRoomate(fridge);
        NiceRoomate n2 = new NiceRoomate(fridge);

        new Thread(h1).start();
        new Thread(h2).start();
        new Thread(h3).start();
        new Thread(n1).start();
        new Thread(n2).start();
    }
}
