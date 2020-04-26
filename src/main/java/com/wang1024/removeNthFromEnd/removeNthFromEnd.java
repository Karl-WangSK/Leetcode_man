package com.wang1024.removeNthFromEnd;

import com.wang1024.twoAdd.ListNode;
import org.w3c.dom.NodeList;

/*
* 给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * @author qqg
 * @return
 */
public class removeNthFromEnd {
    public static ListNode findNth(ListNode head, int n){
        int num=0;
        ListNode temp=head;
        while(temp.next !=null){
            num++;
            temp=temp.next;
        }
        num-=n;
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        temp=dummy;
        while(num>0){
            num--;
            temp=temp.next;
        }
        temp.next=temp.next.next;

        return dummy.next;
    }
    public static void main(String[] args) {
        ListNode a=new ListNode(5);
        a.next=new ListNode(7);
        a.next.next=new ListNode(3);
        a.next.next.next=new ListNode(4);
        ListNode nth = findNth(a, 1);
        System.out.println(nth.val+"="+nth.next.val+"="+nth.next.next.val);
    }
}
