package 动态规划.lowest_price;

public class Lowest_price {

    public int mincostTickets(int[] days, int[] costs) {

        int len=days.length-1 ;
        int min=days[0];
        int max=days[days.length-1];
        int[] dp = new int[max + 30];


        for (int i=max;i >= min;i--){
            if (i==days[len]){
                dp[i]=Math.min(dp[i+1]+costs[0],dp[i+7]+costs[1]);
                dp[i]=Math.min(dp[i],dp[i+30]+costs[2]);
                len--;
            }else{
                dp[i]=dp[i+1];
            }
        }
        return dp[min];

    }
}
