package string;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TinyUrl {

    String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    Map<String, String> map = new HashMap<>();
    Random random = new Random();

    private String generateRandomKey() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String key = generateRandomKey();
        while (map.containsKey(key)) {
            key = generateRandomKey();
        }
        map.put(key, longUrl);
        return key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String code = shortUrl.replace("http://tinyurl.com/", "");
        return map.get(code);
    }

}
