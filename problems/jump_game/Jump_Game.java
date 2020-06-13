package jump_game;

/*
 * @author Karl Wang
 */
public class Jump_Game {

    public static boolean canjump(int[] nums) {
        boolean b = false;
        int len = nums.length - 1;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= index && nums[i] + i > index) {
                index = nums[i]+i;
            }
            b = index >= len ;
        }

        return b;
    }


    public static void main(String[] args) {
        Boolean b = canjump(new int[]{1,1,0,1});
        System.out.println(b);
    }

}
