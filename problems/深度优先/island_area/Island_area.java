package 深度优先.island_area;


public class Island_area {
    int temp = 0;

    void dfs(int[][] grid, int r, int c) {
        int rn = grid.length;
        int cn = grid[0].length;

        if (r < 0 || c < 0 || r >= rn || c >= cn || grid[r][c] == 0) return;
        grid[r][c] = 0;
        temp++;
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);

    }

    public int island(int[][] grid) {

        if (grid.length == 0 || grid == null) return 0;

        int r = grid.length;
        int c = grid[0].length;

        int max_area = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    max_area = Math.max(max_area, temp);
                    temp = 0;
                }
            }
        }
        return max_area;

    }


}
