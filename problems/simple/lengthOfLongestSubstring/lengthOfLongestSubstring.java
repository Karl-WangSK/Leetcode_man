package simple.lengthOfLongestSubstring;

import java.util.HashSet;

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
