package matrix;

import java.util.HashMap;
import java.util.Map;

public class LonelyPixel {

    /**
     * LonelyPixel 1
     * Given a picture consisting of black and white pixels, find the number of black LONELY pixels.
     *
     * The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.
     *
     * A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any
     * other black pixels.
     */
    public int findLonelyPixel(char[][] picture) {
        int rows = picture.length;
        int columns = picture[0].length;

        int[] rowCount = new int[rows];
        int[] colCount = new int[columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (picture[i][j] == 'B') {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (picture[i][j] == 'B') {
                    if (rowCount[i] == 1 && colCount[j] == 1) {
                        count++;
                    } else {
                        //skip the rest of this line
                        break;
                    }
                }
            }
        }

        return count;
    }

    /**
     * LonelyPixel 2
     * Rule1, Row R and column C both contain exactly N black pixels. Rule2, For all rows that have a black pixel at column C, they
     * should be exactly the same as row R
     *
     * Time complexity is O(m*n).
     */
    public int findBlackPixel(char[][] picture, int N) {
        int rows = picture.length;
        int columns = picture[0].length;

        //key is row to string, value is count
        Map<String, Integer> rowMap = new HashMap<>();
        int[] colCount = new int[columns];

        for (int i = 0; i < rows; i++) {
            String str = new String(picture[i]);
            rowMap.put(str, rowMap.getOrDefault(str, 0) + 1);

            for (int j = 0; j < columns; j++) {
                if (picture[i][j] == 'B') {
                    colCount[j]++;
                }
            }
        }

        int count = 0;
        for (Map.Entry<String, Integer> entry : rowMap.entrySet()) {
            String str = entry.getKey();
            int rowsCount = entry.getValue();
            if (rowsCount != N) {
                continue;
            }

            int bs = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == 'B') {
                    bs++;
                }
            }

            if (bs != N) {
                continue;
            }

            int matchedCols = 0;
            for (int j = 0; j < columns; j++) {
                if (colCount[j] == N && str.charAt(j) == 'B') {
                    matchedCols++;
                }
            }

            count += rowsCount * matchedCols;
        }

        return count;
    }
}
