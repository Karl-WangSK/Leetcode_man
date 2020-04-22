package com.wang1024.twoSum;

import java.util.HashMap;
/*
*
给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]


 * @author qqg
 * @return
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.keySet().contains(nums[i])) {
                map.put(nums[i],i);
                System.out.println(nums[i]);
            }
        }

        System.out.println(map);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])&& map.get(target-nums[i])!=i){
                int[] re=new int[]{i,map.get(target-nums[i])};
                return re;
            }
        }

        throw  new IllegalArgumentException("no solution");



    }

    public static void main(String[] args) {
        twoSum(new int[]{2,3,5},7);
    }
}
