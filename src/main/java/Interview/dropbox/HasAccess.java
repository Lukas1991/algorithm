package Interview.dropbox;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HasAccess {

    /**
     * 给很多对(child, parent)这样的文件关系，folders: [folder, parent_folder]
     * 再给定一个set，user对这个set中的文件夹有direct access，对这些文件夹的子文件夹也有access。
     * <p>
     * 查询user对某个给定的folderName有没有访问权限。
     */
    public boolean hasAccess(String[][] folders, Set<String> access, String folderName) {
        //key is folder, value is its parent
        Map<String, String> map = new HashMap<>();
        for (String[] arr : folders) {
            map.put(arr[0], arr[1]);
        }

        String current = folderName;
        while (current != "NONE") {

            if (access.contains(current)) {
                return true;
            } else {
                if (map.containsKey(current)) {
                    current = map.get(current);
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    /**
     * /A
     * _|____/B
     * ____|____/C  <--access
     * ____|____/D
     * _|____/E  <--access
     * ____|____/F
     * _______|____/G
     */
    public static void main(String[] args) {
        HasAccess obj = new HasAccess();

        String[][] folders = {
                {"A", "NONE"},
                {"B", "A"},
                {"C", "B"},
                {"D", "B"},
                {"E", "A"},
                {"F", "E"},
                {"G", "F"}
        };

        Set<String> access = new HashSet<>();
        access.add("C");
        access.add("E");

        System.err.println(obj.hasAccess(folders, access, "C")); //true
        System.err.println(obj.hasAccess(folders, access, "B")); //false
        System.err.println(obj.hasAccess(folders, access, "F")); //true
        System.err.println(obj.hasAccess(folders, access, "G")); //true
        System.err.println(obj.hasAccess(folders, access, "X")); //false
    }
}
