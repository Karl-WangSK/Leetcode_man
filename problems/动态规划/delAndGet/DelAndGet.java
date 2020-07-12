package 动态规划.delAndGet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DelAndGet {

    public static int deleteAndEarn(int[] nums) {

        if (nums.length==0) return 0;
        Arrays.sort(nums);
        int[] arr = new int[nums[nums.length - 1]+1];

        for (int i = 0; i < nums.length; i++) {
           arr[nums[i]]=arr[nums[i]]+1;
        }

        int previous=0;
        int curr=arr[1];
        int temp=0;
        for (int i = 2; i < arr.length; i++) {
            temp=curr;
            curr=Math.max(previous+i*arr[i],curr);
            previous=temp;
        }

        return curr;



    }

    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{ 2,3,4}));
    }

}
