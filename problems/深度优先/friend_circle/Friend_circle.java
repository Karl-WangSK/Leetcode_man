package 深度优先.friend_circle;

import java.util.LinkedList;
import java.util.Queue;

public class Friend_circle {

    static void dfs(int[][] M, int[] visited, int i) {

        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }

    public int findCircleNum(int[][] M) {

        int[] visited = new int[M.length];
        int count = 0;

        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                dfs(M, visited, i);
                count++;
            }

        }
        return count;

    }

    public int findCircleNum2(int[][] M) {

        int[] visited = new int[M.length];
        int count = 0;
        Queue<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < M.length; i++) {
            if (visited[i]==0){
                queue.add(i);
                while (!queue.isEmpty()){
                    Integer n = queue.remove();
                    visited[n]=1;
                    for (int j = 0; j < M.length; j++) {
                        if (M[n][j]==1 && visited[j]==0){
                            queue.add(j);
                        }
                    }
                }
                count++;
            }
        }

        return count;

    }
}
