package com.wang1024.sort_color;
/*
 * @author Karl Wang
 * @return
 */
public class Sort_color {
    public static void sort(int[] nums) {
        int curr = 0;
        int p0 = 0;
        int p2 = nums.length - 1;
        int temp = 0;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                temp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = temp;

            } else if (nums[curr] == 2) {
                temp = nums[p2];
                nums[p2--] = nums[curr];
                nums[curr] = temp;
            } else {
                curr++;
            }
        }

    }
}
