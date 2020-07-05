package Tree.tree_list;

import 深度优先.binary_tree_coin.TreeNode;
import simple.twoAdd.ListNode;

public class Tree_list {

    public boolean isSubPath(ListNode head, TreeNode root) {

        if (head==null || root==null) return false;

        return  isSub(head,root) || isSubPath(head,root.left)|| isSubPath(head,root.right);

    }

    static boolean isSub(ListNode head, TreeNode root){
        if (head==null) return true;

        if (root==null) return false;

        if (root.val!=head.val) return false;

        return isSub(head.next,root.right) || isSub(head.next,root.left);

    }

}
