package Design;

public class PassValueTest2 {

    /**
     * Pass by Reference
     */
    private int i;

    public void set(int d) {
        i = d;
    }

    public int get() {
        return i;
    }

    public void test(PassValueTest2 t) {
        t.i = 2;
        t = new PassValueTest2();
        t.i = 55;
    }

    public PassValueTest2 test2(PassValueTest2 t) {
        t.i = 2;
        t = new PassValueTest2();
        t.i = 55;
        return t;
    }

    public static void main(String[] args) {
        PassValueTest2 d = new PassValueTest2();
        d.test(d);
        System.out.println(d.get()); //2

        d = d.test2(d);
        System.out.println(d.get()); //55
    }

}
