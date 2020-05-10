package com.wang1024.container_with_most_water;
/*
给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
说明：你不能倾斜容器，且 n 的值至少为 2。

图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例：
输入：[1,8,6,2,5,4,8,3,7]
输出：49

 * @author Karl Wang
 * @return
 */
public class Container_with_most_water {

    public static int maxArea(int[] height){
        int max_water=0;
        int start=0;
        int end=height.length-1;

        while(start<end){
            if (height[start]<=height[end]){
                max_water = Math.max(height[start] * (end - start),max_water);
                while(height[start+1]<=height[start] && start+1 <end) start++;
                start++;
            }else{
                max_water =  Math.max(height[end] * (end - start),max_water);
                while(height[end-1]<=height[end] && start <end-1) end--;
                end--;
            }
        }
        return max_water;
    }

    public static void main(String[] args) {
        int max_water = maxArea(new int[]{2, 4, 5, 7, 3, 9, 6, 5, 3});

        System.out.println(max_water);

    }

}
