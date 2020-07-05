package simple.removeNthFromEnd;


import simple.twoAdd.ListNode;

public class removeNthFromEnd {

    public static ListNode findNth(ListNode head, int n) {
        int num = 0;
        ListNode temp = head;
        while (temp.next != null) {
            num++;
            temp = temp.next;
        }
        num -= n;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        temp = dummy;
        while (num > 0) {
            num--;
            temp = temp.next;
        }
        temp.next = temp.next.next;

        return dummy.next;
    }

    /*
     *   一次循环完成
     * @author qqg
     * @return com.wang1024.twoAdd.ListNode
     */
    public static ListNode findNth2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(5);
        a.next = new ListNode(7);
        a.next.next = new ListNode(3);
        a.next.next.next = new ListNode(4);
        ListNode nth = findNth(a, 1);

        System.out.println(nth.val + "=" + nth.next.val + "=" + nth.next.next.val);
    }
}
