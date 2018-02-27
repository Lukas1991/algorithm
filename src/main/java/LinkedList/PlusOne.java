package LinkedList;

public class PlusOne {

    //从后往前扫，如果是9，变成0。第一个不是9的数字，+1 返回
    //注意999 -> 1000的情况
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i]++;
                return digits;
            }
        }

        int[] res = new int[n + 1];
        res[0] = 1;
        return res;
    }

    //两个指针，j往后扫，指针i指向最后一个不是9的node.
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode j = dummy;
        ListNode i = dummy; //pointer to last digit not 9

        while (j.next != null) {
            j = j.next;
            if (j.val != 9) {
                i = j;
            }
        }

        //j is the last
        if (j.val != 9) {
            j.val = j.val + 1;
            return dummy.next;
        } else {
            i.val = i.val + 1;
            i = i.next;
            while (i != null) {
                i.val = 0;
                i = i.next;
            }

            if (dummy.val == 0) {
                return dummy.next;
            } else {
                return dummy;
            }
        }
    }
}
