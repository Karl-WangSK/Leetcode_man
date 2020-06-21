package 深度优先.island_area;


public class Island_area {


    static int  dfs(int[][] grid,int r,int c,int temp){
        int rn=grid.length;
        int cn=grid[0].length;

        if (r<0 ||c<0 ||r>=rn ||c>=cn ||grid[r][c]==0) return temp;
        grid[r][c]=0;
        temp++;
        temp = dfs(grid, r - 1, c, temp);
        temp =dfs(grid,r+1,c,temp);
        temp =dfs(grid,r,c-1,temp);
        temp =dfs(grid,r,c+1,temp);
        return temp;
    }

    static int island(int[][] grid){

        if (grid.length==0 ||grid==null) return  0;

        int r=grid.length;
        int c=grid[0].length;

        int max_area=0;
        int temp=0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                if (grid[i][j]==1){
                    temp=dfs(grid,i,j,temp);
                    max_area= Math.max(max_area,temp);
                    temp=0;
                }
            }
        }
        return max_area;

    }



}
