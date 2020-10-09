package 深度优先.curriculum;

import java.util.ArrayList;

public class curriculum {
    static boolean flag = true;
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int[] ints = new int[numCourses];

        if (prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                ints[i] = i;
            }
        }else {
            for (int i = 0; i < prerequisites.length; i++) {
                if (!list.contains(prerequisites[i][1])) {
                    bfs(prerequisites[i][1], prerequisites, list, i);
                }
                if (!list.contains(prerequisites[i][0])) {
                    list.add(prerequisites[i][0]);
                }
            }
            for (int i = list.size()-1 ; i >=0; i--) {
                ints[i] = list.get(i);
            }
        }
        if (flag){
            return ints;
        }else{
            return new int[0];
        }


    }

    public static void bfs(int pre, int[][] prerequisites, ArrayList<Integer> list, int index) {
        for (int i = 0; i < prerequisites.length; i++) {
            if (prerequisites[i][0] == pre && i >index) {
                bfs(prerequisites[i][1], prerequisites, list, i);
            }else if(prerequisites[i][0] == pre && i <=index){
                flag= false;
                return ;
            }
        }
        if (!list.contains(pre)) {
            list.add(pre);
        }
    }

    public static void main(String[] args) {
        int[][] ints = new int[1][2];
        ints[0][0] = 1;
        ints[0][1] = 0;
//        ints[1][0] = 2;
//        ints[1][1] = 0;
//        ints[2][0] = 3;
//        ints[2][1] = 1;
//        ints[3][0] = 3;
//        ints[3][1] = 2;
        int[] order = findOrder(2, ints);
        for (int i = 0; i < order.length; i++) {
            System.out.println(order[i]);
        }
    }
}
