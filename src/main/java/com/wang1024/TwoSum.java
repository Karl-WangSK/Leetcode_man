package com.wang1024;

import java.util.HashMap;

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
