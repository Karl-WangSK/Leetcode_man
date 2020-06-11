package com.wang1024.word_break;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Word_break {
    public  static boolean wordBreak(String s, List<String> wordDict){
        HashSet<String> word_set = new HashSet<String>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0]=true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++){
                if(dp[j] && word_set.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return  dp[s.length()];
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("aaaa");
        list.add("dog");
        System.out.println(wordBreak("aaaadog",list));
    }
}
