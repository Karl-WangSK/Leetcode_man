package 贪心算法.clip;

public class Clip {

    public  static int clip(int[][] clips, int T){

        int l=0;
        int max_r=0;
        int n=0;

        for (int i = 0; i < T; i++) {
            for (int j = 0; j < clips.length; j++) {

                if(clips[j][0]<=l) max_r=Math.max(max_r,clips[j][1]);

            }
            l=max_r;
            n++;
            if (max_r>=T) return n;
        }
        return -1;

    }
}
