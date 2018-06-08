package Interview.amzn.oa;

import java.util.ArrayList;
import java.util.List;

public class LogFile {

    public List<String> reorderLines(int logFileSize, List<String> logLines) {
        List<String> numberLines = new ArrayList<>();
        List<Line> letterLines = new ArrayList<>();

        for (String line : logLines) {
            int index = line.indexOf(" ");
            String id = line.substring(0, index);
            String letters = line.substring(index + 1);

            char first = letters.charAt(0);
            if (Character.isDigit(first)) {
                numberLines.add(line);
            } else {
                Line l = new Line(id, letters.toLowerCase(), line);
                letterLines.add(l);
            }
        }

        letterLines.sort((l1, l2) -> {
            if (l1.letters.equals(l2.letters)) {
                return l1.id.compareTo(l2.id);
            } else {
                return l1.letters.compareTo(l2.letters);
            }
        });

        List<String> res = new ArrayList<>();
        for (Line line : letterLines) {
            res.add(line.line);
        }
        res.addAll(numberLines);

        return res;
    }


    class Line {
        String id;
        String letters;
        String line;

        public Line(String id, String letters, String line) {
            this.id = id;
            this.letters = letters;
            this.line = line;
        }
    }

    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("abc");
//        list.add("1bc");
//        list.sort((s1, s2) -> s1.compareTo(s2));

        //System.err.println(list.toString());


        List<String> logLines = new ArrayList<>();
        logLines.add("a1 9 2 3 1");
        logLines.add("g1 Act car");
        logLines.add("zo4 4 7");
        logLines.add("ab1 off KEY dog");
        logLines.add("a8 act car");

        LogFile obj = new LogFile();
        List<String> res = obj.reorderLines(logLines.size(), logLines);
        System.err.println(res.toString());
    }
}
