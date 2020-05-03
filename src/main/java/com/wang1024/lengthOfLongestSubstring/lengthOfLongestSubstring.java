package com.wang1024.lengthOfLongestSubstring;

import java.util.HashSet;
/*
* 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

 * @author qqg
 * @return
 */
public class lengthOfLongestSubstring {

    public  static int process(String s){
        HashSet<Character> set = new HashSet<Character>();
        int n=s.length();

        int ans=0;
        int lt=-1;
        for (int i = 0; i < n; i++) {
            if (i!=0){
                set.remove(s.charAt(i-1));
            }

            while(lt+1<n && !set.contains(s.charAt(lt+1))){
                set.add(s.charAt(lt + 1));
                lt++;

            }
            ans =Math.max(ans,set.size());

        }

        return ans;
    }

    public static void main(String[] args) {
        int n = process("assadd23456789dddasssadsdddbnmls");
        System.out.println(n);

    }
}
