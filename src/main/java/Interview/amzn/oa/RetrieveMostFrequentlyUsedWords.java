package Interview.amzn.oa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RetrieveMostFrequentlyUsedWords {

    List<String> retrieveMostFrequentlyUsedWords(String literatureText, List<String> wordsToExclude) {
        Set<String> excludeSet = new HashSet<>();
        for (String word : wordsToExclude) {
            excludeSet.add(word.toLowerCase());
        }

        Map<String, Integer> map = new HashMap<>();
        int maxCount = 0;

        int i = 0;
        while (i < literatureText.length()) {
            if (!Character.isLetter(literatureText.charAt(i))) {
                i++;
                continue;
            }

            int end = i + 1;
            while (end < literatureText.length() && Character.isLetter(literatureText.charAt(end))) {
                end++;
            }

            String word = literatureText.substring(i, end);
            word = word.toLowerCase();

            if (!excludeSet.contains(word)) {
                int count = map.getOrDefault(word, 0) + 1;
                map.put(word, count);
                maxCount = Math.max(maxCount, count);
            }

            i = end + 1;
        }

        List<String> res = new ArrayList<>();

        int finalMaxCount = maxCount;
        map.forEach((word, count) -> {
            if (count == finalMaxCount) {
                res.add(word);
            }
        });

        return res;
    }

    public static void main(String[] args) {
        RetrieveMostFrequentlyUsedWords obj = new RetrieveMostFrequentlyUsedWords();

        String[] arr = {"and", "he", "the", "to", "is", "Jack", "Jill"};
        List<String> wordsToExclude = Arrays.stream(arr).collect(Collectors.toList());
        String literatureText = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food.";

        List<String> res = obj.retrieveMostFrequentlyUsedWords(literatureText, wordsToExclude);
        System.err.println(res.toString());
    }
}
