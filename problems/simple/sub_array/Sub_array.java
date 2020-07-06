package simple.sub_array;

import java.util.HashMap;

public class Sub_array {

    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum=0;
        int re=0;
        for (int i = 0; i < k; i++) {
            sum+=arr[i];
        }

        int add=sum-k*threshold;
        if (add>=0){
            re++;
        }

        int pos=k;
        for (int i = 0; i < arr.length-k; i++) {
            add=add+arr[pos++]-arr[i];
            if (add>=0) re++;
        }

        return re;

    }

    public static void main(String[] args) {
        int[] ints = {2, 2, 2, 2, 5, 5, 5, 8, 100};

        int i = numOfSubarrays(ints, 3, 4);
        System.out.println(i);
    }

}
