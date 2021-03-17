package simple.permutation;

import scala.Int;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Permutation {

    public static void main(String[] args) {
        int[] nums= new int[]{1,2,3};
        List<List<Integer>> lists = permute(nums);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> lists = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        return perm(list, 0, nums.length - 1, lists);
    }

    public static List<List<Integer>> perm(List<Integer> nums, int start, int end, List<List<Integer>> lists){
        if (start == end) {
            lists.add(new ArrayList<>(nums));
        }

        for (int i = start; i <= end; i++) {
            Collections.swap(nums, start, i);
            perm(nums, start + 1, end, lists);
            Collections.swap(nums, i, start);
        }
        return lists;
    }

}
