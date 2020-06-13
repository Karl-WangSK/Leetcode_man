package com.wang1024.贪心算法.cook_order;

import java.util.Arrays;

public class Cook_order {

    public static  int cook(int[] satisfaction){

        Arrays.sort(satisfaction);

        int sum=0;
        int res=0;


        for (int i = satisfaction.length-1; i >=0; i--) {
            sum+=satisfaction[i];
            if (sum<=0) break;
            res+=sum;
        }
        return  res;

    }

}
