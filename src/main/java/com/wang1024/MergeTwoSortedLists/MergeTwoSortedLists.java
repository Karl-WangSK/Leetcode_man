package com.wang1024.MergeTwoSortedLists;

import com.wang1024.twoAdd.ListNode;

public class MergeTwoSortedLists {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode listnode = new ListNode(-1);
        ListNode temp = listnode;

        while (l2 != null && l1 != null) {
            if  (l1.val >= l2.val) {
                temp.next = l2;
                l2 = l2.next;
            } else {
                temp.next = l1;
                l1 = l1.next;
            }
            temp=temp.next;

        }
        temp.next= l1==null?l2 :l1;

        return listnode.next;
    }

    public static void main(String[] args) {

        ListNode a = new ListNode(1);
        a.next = new ListNode(3);
        a.next.next = new ListNode(5);

        ListNode b = new ListNode(1);
        b.next = new ListNode(4);
        b.next.next = new ListNode(5);

        ListNode l = mergeTwoLists(a, b);

        System.out.println(l.val+"=="+l.next.val+"=="+l.next.next.val+"=="+l.next.next.next.val);


    }



}



