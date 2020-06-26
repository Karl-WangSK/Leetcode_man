package Tree.tree_balance;

import 深度优先.binary_tree_coin.TreeNode;

public class Tree_balance {

    static boolean judge=true;

    public static  boolean isBalanced(TreeNode root) {

        if (root==null) return true;

        dfs(root);

        return judge;

    }


    static int dfs(TreeNode root){

        if (root==null) return 1;

        int l=dfs(root.left);
        int r=dfs(root.right);
        System.out.println(Math.abs(l-r));
        if (Math.abs(l-r)>1) {
            judge=false;
        }
        return Math.max(l,r)+1;
    }

    public static void main(String[] args) {
        System.out.println(isBalanced(new TreeNode(1)));
        System.out.println(Math.abs(1-1));
    }

}
