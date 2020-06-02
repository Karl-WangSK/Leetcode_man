package com.wang1024.swap_nodes;

import com.wang1024.twoAdd.ListNode;

public class Swap_nodes_in_pairs {

    public static ListNode process(ListNode head){
        if (head ==null || head.next ==null ){
            return  head;
        }

        ListNode firstnode=head;
        ListNode secnode=head.next;

        firstnode.next=process(secnode.next);
        secnode.next=firstnode;

        return secnode;

    }


    public static void main(String[] args) {

        ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(4);
        l1.next=l2;
        ListNode l3 = process(l1);

        System.out.println(l3.val);

    }
}
