package 回溯算法.queen;

import java.util.ArrayList;
import java.util.List;

public class Queen {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<List<String>>();

        char[][] nums = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j]='.';
            }
        }


        return  list;

    }

    static void backtrace(char[][] nums,int start,List<List<String>> list){

        if (start==nums.length){

        }

        for (int row = 0; row < start; row++) {
            boolean isok=true;

            for (int col = 0; col < nums.length; col++) {
                if (nums[row][col]=='Q'){
                    isok=false;
                    break;
                }


            }


        }



    }
}
