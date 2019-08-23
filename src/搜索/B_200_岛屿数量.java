package 搜索;

import java.util.LinkedList;
import java.util.Queue;

public class B_200_岛屿数量 {

    //方向数组（上，下，左，右）
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    /**
     * 深度优先搜索岛屿的数量
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid){
        if (grid.length == 0){
            return 0;
        }
        //岛屿数量
        int island_num = 0;
        //用来标记地图grid中哪些位置已经被搜索过了
        int[][] mark = new int[grid.length][grid[0].length];
        //搜索地图
        for (int i = 0;i < grid.length;i ++){
            for (int j =0;j < grid[i].length;j ++){
                //如果该位置是一个岛屿，且没有被搜索过，就深度优先搜索该位置
                if (grid[i][j] == '1' && mark[i][j] == 0){
                    DFS(grid,mark,i,j);
                    island_num ++;
                }
            }
        }
        return island_num;
    }

    /**
     * 深度优先搜索坐标为i，j的岛屿
     * @param grid
     * @param mark
     * @param x
     * @param y
     */
    private void DFS(char[][] grid, int[][] mark, int x, int y) {
        //标记为已搜索
        mark[x][y] = 1;
        //顺序深搜四个方向，这里没有回溯，因为没有return
        for (int i = 0;i < 4;i ++){
            int new_x = x + dx[i];
            int new_y = y + dy[i];
            //如果超出了地图范围，就搜索下一个方向
            if (new_x < 0 || new_y < 0 || new_x >= grid.length || new_y >= grid[new_x].length){
                continue;
            }
            //如果该位置是一个岛屿，且没有被搜索过，就深度优先搜索该位置
            if (grid[new_x][new_y] == '1' && mark[new_x][new_y] == 0){
                DFS(grid,mark,new_x,new_y);
            }
        }
    }

    /**
     * 广度优先搜索岛屿的数量
     * @param grid
     * @return
     */
    public int numIslands_(char[][] grid){
        if (grid.length == 0){
            return 0;
        }
        //岛屿数量
        int island_num = 0;
        //用来标记地图grid中哪些位置已经被搜索过了
        int[][] mark = new int[grid.length][grid[0].length];
        //搜索地图
        for (int i = 0;i < grid.length;i ++){
            for (int j =0;j < grid[i].length;j ++){
                //如果该位置是一个岛屿，且没有被搜索过，就广度优先搜索该位置
                if (grid[i][j] == '1' && mark[i][j] == 0){
                    BFS(grid,mark,i,j);
                    island_num ++;
                }
            }
        }
        return island_num;
    }

    /**
     * 广度优先搜索坐标为i，j的岛屿
     * @param grid
     * @param mark
     * @param x
     * @param y
     */
    private void BFS(char[][] grid, int[][] mark, int x, int y) {
        //广度优先搜索队列
        Queue<int[]> queue = new LinkedList<>();
        int[] coordinate = {x,y};
        queue.add(coordinate);
        mark[x][y] = 1;
        while (!queue.isEmpty()){
            x = queue.peek()[0];
            y = queue.peek()[1];
//            mark[x][y] = 1;（不能在出队时，才标记为已经被搜索，防止坐标重复入队）
            queue.remove();
            for (int i = 0;i < 4;i ++){
                int new_x = x + dx[i];
                int new_y = y + dy[i];
                if (new_x < 0 || new_y < 0 || new_x >= grid.length || new_y >= grid[new_x].length){
                    continue;
                }
                if (grid[new_x][new_y] == '1' && mark[new_x][new_y] == 0){
                    int[] new_coordinate = {new_x,new_y};
                    queue.add(new_coordinate);
                    mark[new_x][new_y] = 1;
                }
            }
        }
    }

}
