package 贪心算法.garden_water;

public class Garden_water {

    public  static  int water(int n, int[] ranges){

        int count=0;
        int l=0;
        int max_r=0;

        while (max_r<n){
            for (int i = 0; i <= n; i++) {
                if (i - ranges[i] <= l) max_r = Math.max(i + ranges[i], max_r);
            }
            count++;
            l=max_r;
            if (count>n) return -1;
        }
        return count;
    }

    public static void main(String[] args) {
        int water = water(5, new int[]{3, 4, 1, 1, 0, 0});
    }

}
