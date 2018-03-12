package Interview.dropbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

// Follow up question: Are there any circumstances which could lead to false negative?
// files with same size and same md5 hash. If we compare byte by byte, then we can avoid false positive.
// metadata of the file : type, size, creator, created date...
public class FindDuplicateFiles {
    /**
     * If files size are not large, we can group files by file content.
     * <p>
     * BFS find all files under path, First group files by size, then group files by MD5 hash. (md5 on first chunk of data, or entire data).
     * Then compare byte by byte.
     * 有的文件不能fit memory, md5 hash the first 1kb, then the second 1kb, and so on until read the end.
     */
    public List<List<String>> findDuplicatesHashSizeAndMD5(String path) throws IOException {
        List<List<String>> res = new ArrayList<>();
        if (path == null || path.length() == 0) return res;

        Map<Long, List<String>> sizeMap = bfsWithSize(path); //group files by size

        // Iterate through the files with the same size (potentially to be duplicate files)
        for (List<String> sameSizeList : sizeMap.values()) {

            Map<String, List<String>> md5Map = new HashMap<>(); // group files by MD5 hash
            for (String f : sameSizeList) {
                File file = new File(f);
                String hash = getMD5(file);

                if (!md5Map.containsKey(hash)) {
                    md5Map.put(hash, new ArrayList<>());
                }

                md5Map.get(hash).add(f);
            }

            for (List<String> list : md5Map.values()) {
                if (list.size() > 1) {
                    //same size, same md5 hash, and then compare bytes
                    //res.addAll(compareByteStream(list));
                    res.add(list);
                }
            }
        }

        return res;
    }

    //Use bfs to get all the files in the directory, return the path of all files
    private Map<Long, List<String>> bfsWithSize(String path) {
        Map<Long, List<String>> sizeMap = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(path);

        while (!queue.isEmpty()) {
            String curPath = queue.poll();

            File file = new File(curPath);

            if (file.isFile()) {
                long size = file.length();
                if (!sizeMap.containsKey(size)) {
                    sizeMap.put(size, new ArrayList<>());
                }

                sizeMap.get(size).add(curPath);

            } else if (file.isDirectory()) {
                String[] files = file.list();
                for (String s : files) {
                    String newPath = curPath + "/" + s;
                    queue.offer(newPath);
                }
            }
        }

        return sizeMap;
    }

    //有的文件不能fit memory, we do not calculate MD5 value of the whole file. Instead we divide the file into blocks of 1kb.
    //First hash the file use the first 1kb and then hash by the second 1kb.....and so on.
    String getMD5(File file) throws IOException {
        SHA256 sha256 = new SHA256();
        FileInputStream stream = new FileInputStream(file);

        byte[] dataBytes = new byte[1024];

        int nread; //number of bytes read

        do {
            nread = stream.read(dataBytes);
            sha256.update(dataBytes);
        } while (nread != -1);

        return sha256.compute();
    }

    class SHA256 {
        public void update(byte[] bytes) {
        }

        public String compute() {
            return "";
        }
    }

    List<List<String>> compareByteStream(List<String> list) throws IOException {
        boolean[] checked = new boolean[list.size()];
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            checked[i] = true;
            String file1 = list.get(i);
            FileInputStream stream1 = new FileInputStream(file1);

            for (int j = i + 1; j < list.size(); j++) {
                if (!checked[j]) {
                    String file2 = list.get(j);
                    FileInputStream stream2 = new FileInputStream(file2);

                    if (sameStream(stream1, stream2)) {
                        if (!map.containsKey(file1)) {
                            List<String> newlist = new ArrayList<>();
                            newlist.add(file1);
                            map.put(file1, newlist);
                        }

                        map.get(file1).add(file2);
                    }

                    checked[j] = true;
                }
            }
        }

        return new ArrayList<>(map.values());
    }

    boolean sameStream(FileInputStream stream1, FileInputStream stream2) throws IOException {
        byte[] data1 = new byte[1024];
        byte[] data2 = new byte[1024];
        int n = 0;
        do {
            n = stream1.read(data1);
            stream2.read(data2);

            for (int i = 0; i < data1.length; i++) {
                if (data1[i] != data2[i]) return false;
            }

        } while (n != -1);

        return true;
    }

    //<-------------------------------findDuplicatesMD5Only---------------------------------------------------------------------------------------------------->
    public List<List<String>> findDuplicatesMD5Only(String path) throws IOException {
        List<List<String>> res = new ArrayList<>();
        if (path == null || path.length() == 0) return res;

        List<String> filesList = bfs(path); // list all files in the directory
        Map<String, List<String>> hMap = new HashMap<>(); // map: hash value ---> list of file paths

        // Hashing each file and put it into according bucket in the HashMap
        for (String f : filesList) {
            File file = new File(f);
            String hash = getMD5(file);

            if (hMap.containsKey(hash)) {
                List<String> value = hMap.get(hash);
                value.add(f);
                hMap.put(hash, value);
            } else {
                List<String> value = new ArrayList<>();
                value.add(f);
                hMap.put(hash, value);
            }
        }

        for (List<String> value : hMap.values())
            if (value.size() > 1)
                res.add(value);

        return res;
    }

    // Use bfs to get all the files in the directory, return the path of all files
    private List<String> bfs(String path) {
        List<String> filesList = new ArrayList<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(path);

        while (!queue.isEmpty()) {
            String curPath = queue.poll();

            File file = new File(curPath);

            if (file.isFile()) {
                filesList.add(curPath);
            } else if (file.isDirectory()) {
                String[] files = file.list();
                for (String s : files) {
                    String newPath = curPath + "/" + s;
                    queue.offer(newPath);
                }
            }
        }

        return filesList;
    }


    //<-------------------------Leetcode---------------------------------------------------------------------------------------------------------->

    /**
     * @param paths ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
     */
    public List<List<String>> findDuplicate(String[] paths) {
        //key is content, value is paths
        Map<String, List<String>> map = new HashMap<>();

        for (String path : paths) {
            String[] arr = path.split(" ");
            String dir = arr[0];
            for (int i = 1; i < arr.length; i++) {
                String s = arr[i];
                int index = s.indexOf("(");
                String content = s.substring(index + 1, s.length() - 1);

                if (!map.containsKey(content)) {
                    map.put(content, new ArrayList<>());
                }

                String filePath = dir + "/" + s.substring(0, index);
                map.get(content).add(filePath);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (List<String> list : map.values()) {
            if (list.size() > 1) {
                res.add(list);
            }
        }
        return res;
    }
}
