package com.wang1024.threeAdd;

import java.util.*;


/*
*给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。
 
示例：

给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]

 * @author qqg
 * @return
 */
public class ThreeAdd {

    public static List<List<Integer>> process(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return Collections.emptyList();
        }

        Arrays.sort(arr);
        ArrayList<List<Integer>> list = new ArrayList<List<Integer>>();

        for (int i = 0; i < arr.length - 2; i++) {
            int head = i + 1;
            int tail = arr.length - 1;
            if (arr[0] > 0) break;
            if (i > 0 && arr[i] == arr[i - 1]) continue;
            while (head < tail) {
                if (arr[head] + arr[tail] + arr[i] == 0) {
                    list.add(Arrays.asList(arr[head], arr[tail], arr[i]));
                    while (head + 1 < tail && arr[head] == arr[head + 1]) {
                        head++;
                    }
                    while (tail - 1 > head && arr[tail] == arr[tail - 1]) {
                        tail--;
                    }
                }
                if (arr[head] + arr[tail] + arr[i] <= 0) {
                    head++;
                } else {
                    tail--;
                }

            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] ints = {-1, 2, 4, 6, -5, -3, 0, -3, -4};
        List<List<Integer>> list = process(ints);
        for (List<Integer> integers : list) {
            System.out.println(integers.toString());
        }
    }
}
