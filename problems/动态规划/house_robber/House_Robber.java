package com.wang1024.动态规划.house_robber;

public class House_Robber {

    public static int rob(int[] nums){
        int previous=0;
        int current=0;
        for (int x: nums){
            int temp=current;
            current = Math.max(previous + x, current);
            previous=temp;
        }
        return current;
    }

    public static void main(String[] args) {
        int money = rob(new int[]{1, 4, 5, 6, 7, 34, 6});
        System.out.println(money);
    }
}
