package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadItemFile {

    public static String convert(String[] arr) {
        String format = "    {\n" +
                "        \"id\": %s,\n" +
                "        \"title\": \"%s\",\n" +
                "        \"difficulty\": \"%s\",\n" +
                "        \"done\": 0,\n" +
                "        \"description\": \"\"\n" +
                "    },";
        return String.format(format, arr[0], arr[1].trim(), arr[3]);
    }
    public static void main(String[] args) {

        try {
            File file = new File("/Users/chuyu/gitRepo/algorithm/src/main/resources/Amazon.txt");

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println("var items = [");

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //line = line.trim() + "\t";
                //line += bufferedReader.readLine().trim();
//                if (line.isEmpty()) {
//                    continue;
//                }

                String[] arr = line.split("\t");
                System.out.println(convert(arr));
            }
            fileReader.close();

            System.out.println("];");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
