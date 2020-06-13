package 动态规划.coin;

public class Coin {
    public  static int coin(int n){
        int[] coins=new int[]{1,5,10,25};
        int[] dp=new int[n+1];

        dp[0]=1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - coins[i]])% 1000000007;
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        System.out.println(coin(100));
    }
}
