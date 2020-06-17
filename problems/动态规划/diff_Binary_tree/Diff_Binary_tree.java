package 动态规划.diff_Binary_tree;

public class Diff_Binary_tree {

    public static int tree(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];

            }
        }
        return dp[n];
    }

}
