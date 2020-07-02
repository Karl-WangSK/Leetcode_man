package Tree.path_sum;

import 深度优先.binary_tree_coin.TreeNode;

public class Path_sum {

    public int pathSum(TreeNode root, int sum) {

        if (root==null) return 0;

        return findroot(root,sum)+pathSum(root.left,sum)+pathSum(root.right,sum);

    }

    static int findroot(TreeNode root,int target){
        if (root==null) return 0;
        int temp=target-root.val;
        int count=temp==0?1:0;

        return count+findroot(root.left, temp)+findroot(root.right,temp);
    }

}
