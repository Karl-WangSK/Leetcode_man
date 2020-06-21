package 深度优先.binary_tree_coin;

public class Binary_tree_coin {
     int res=0;
    public int distributeCoins(TreeNode root) {

        dfs(root);
        return res;
    }

     int dfs(TreeNode root){
        if (root==null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        res+=Math.abs(left)+Math.abs(right);

        return root.val+left+right-1;

    }




}
