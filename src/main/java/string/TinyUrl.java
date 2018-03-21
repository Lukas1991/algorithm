package string;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TinyUrl {

    String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    Map<String, String> map = new HashMap<>();
    Map<String, String> longToShort = new HashMap<>();
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
    public String longToShort(String longUrl) {
        if (longToShort.containsKey(longUrl)) {
            return longToShort.get(longUrl);
        }

        String key = generateRandomKey();
        while (map.containsKey(key)) {
            key = generateRandomKey();
        }
        map.put(key, longUrl);
        longToShort.put(longUrl, "http://tiny.url/" + key);
        return "http://tiny.url/" + key;
    }

    // Decodes a shortened URL to its original URL.
    public String shortToLong(String shortUrl) {
        String code = shortUrl.replace("http://tiny.url/", "");
        return map.get(code);
    }


    public String createCustom(String long_url, String key) {
        if (key.length() < 6) {
            return "error";
        }
        if (map.containsKey(key)) {
            return "error";
        }

        map.put(key, long_url);
        return "http://tiny.url/" + key;
    }

    public static void main(String[] args) {
        TinyUrl obj = new TinyUrl();
        System.err.println(obj.createCustom("http://www.lintcode.com/", "lccode"));

        System.err.println(obj.longToShort("http://www.lintcode.com/problem/"));

        System.err.println(obj.shortToLong("http://tiny.url/lccode"));
        System.err.println(obj.createCustom("http://www.lintcode.com/", "lc"));
        System.err.println(obj.createCustom("http://www.lintcode.com/en/ladder/", "lccode"));

        System.err.println(obj.longToShort("http://www.lintcode.com/problem/"));
    }
}
