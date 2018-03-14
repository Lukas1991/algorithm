package Interview.dropbox;

/**
 * .
 * NASA selects Dropbox as its official partner, and we’re tasked with managing
 * a panorama for the universe. The Hubble telescope (or some other voyager we
 * have out there) will occasionally snap a photo of a sector of the universe,
 * and transmit it to us. You are to help write a data structure to manage this.
 * For the purpose of this problem, assume that the observable universe has been
 * divided into 2D sectors. Sectors are indexed by x- and y-coordinates.
 */
public class SpacePanorama {

    String[][] grid; //不get stalest, recent的话
    Node[][] nodes;  //get stalest, recent的话
    int rows;
    int cols;

    /**
     * initializes the data structure. rows x cols is the sector layout
     * width, height can be as large as 1K each.
     */
    public SpacePanorama(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        grid = new String[rows][cols];
        //set initial fileName
        for (int i = 0; i < rows; i++) {
            File file = new File("/" + i);
            file.mkdir();
            for (int j = 0; j < cols; j++) {
                grid[i][j] = getFileName(i, j);
            }
        }

        nodes = new Node[rows][cols];
        //chain nodes
        Node dummy = new Node(-1, -1);
        Node cur = dummy;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cur.next = new Node(i, j);
                cur = cur.next;
            }
        }
        head = dummy.next;
        end = cur;
    }

    /**
     * The Hubble will occasionally call this (via some radio wave communication)
     * to report new imagery for the sector at (y, x)
     * Images can be up to 1MB in size.
     */
    public void update(int y, int x, Image image) {
        if (y < 0 || y >= rows || x < 0 || x >= cols || image == null) {
            return;
        }

        String fileName = grid[y][x];
        File file = new File(fileName);
        file.write(image.getBytes());

        Node node = nodes[y][x];
        fileName = node.fileName;
        remove(node);
        addToHead(node);
    }

    // /y/x_Image.jpg
    String getFileName(int y, int x) {
        return "/" + y + "/" + x + "_Image.jpg";
    }

    /**
     * NASA will occasionally call this to check the view of a particular sector.
     */
    public Image fetch(int y, int x) {
        if (y < 0 || y >= rows || x < 0 || x >= cols) {
            return null;
        }

        String fileName = grid[y][x];
        File file = new File(fileName);
        if (file.exists()) {
            byte[] bytes = file.read();
            return new Image(bytes);
        } else {
            return null;
        }
    }

    /**
     * return the 2D index of the sector that has the stalest data.
     * the idea is that this may help the telescope decide where to aim next.
     */

    private Node head;
    private Node end;

    public Sector getStalestSector() {
        if (end == null) return null;
        return end.key;
    }

    public Sector getRecentSector() {
        if (head == null) return null;
        return head.key;
    }

    class File {
        public File(String path) {
        }

        public Boolean exists() {
            return true;
        }

        public byte[] read() {
            return null;
        }

        public void write(byte[] bytes) {
        }

        public void mkdir() {
        }
    }

    class Image {
        public Image(byte[] bytes) {
        }

        byte[] getBytes() {
            return null;
        } // no more than 1MB in size.
    }

    class Sector {
        public Sector(int x, int y) {
        }

        int getX() {
            return 0;
        }

        int getY() {
            return 0;
        }
    }

    class Node {
        Sector key;
        String fileName;
        Node pre;
        Node next;

        public Node(int y, int x) {
            key = new Sector(x, y);
            fileName = getFileName(x, y);
        }
    }

    private void remove(Node node) {
        Node preNode = node.pre;
        Node nextNode = node.next;

        if (preNode == null) {
            head = nextNode;
        } else {
            preNode.next = nextNode;
        }

        if (nextNode == null) {
            end = preNode;
        } else {
            nextNode.pre = preNode;
        }

        node.pre = null;
        node.next = null;
    }

    private void addToHead(Node node) {
        if (head == null) {
            head = node;
            end = node;
        } else {
            node.next = head;
            head.pre = node;
            head = node;
        }
    }
}
