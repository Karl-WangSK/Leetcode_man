package com.wang1024.decode_ways;

public class Decode_ways {

    public static int decode(String s) {
        if (s.charAt(0)  == '0') return 0;
        int current = 1;
        int previous = 1;
        int temp = 0;
        //34234234
        for (int i = 1; i < s.length(); i++) {
            temp = current;
            if (s.charAt(i) == '0') {
                if (s.charAt(i-1) != '1' && s.charAt(i-1) != '2') return 0;
            } else if(s.charAt(i-1) == '1' ||
                    (s.charAt(i-1) == '2' && s.charAt(i) >= '1' && s.charAt(i) <= '6') ){
                current=previous+current;
            }
            previous = temp;
        }


        return current;

    }

    public static void main(String[] args) {
        int num = decode("32143");
        System.out.println(num);
    }
}
