package 动态规划.coin2;

public class Coins2 {

    public static int maxCombination(int amount, int[] coins){

        int[] dp = new int[amount + 1];
        dp[0]=1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < dp.length; j++) {
                dp[j]=dp[j]+dp[j-coins[i]];

            }
        }
        return  dp[amount];

    }
}
