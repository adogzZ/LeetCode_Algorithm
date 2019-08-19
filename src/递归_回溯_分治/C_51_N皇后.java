package 递归_回溯_分治;

import java.util.ArrayList;
import java.util.List;

public class C_51_N皇后 {

    //方向数组
    static int[] dx = {-1,1,0,0,-1,-1,1,1};
    static int[] dy = {0,0,-1,1,-1,1,-1,1};

    /**
     * 递归放置第n行的皇后，从第一列开始尝试，更新棋盘，回溯进行下一列的尝试。
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n){
        //结果集
        List<List<String>> result = new ArrayList<>();
        //解
        List<String> item = new ArrayList<>();
        //初始化棋盘
        int[][] mark = new int[n][n];
        //初始化解
        for (int i = 0;i < n;i ++){
            String str = "";
            for (int j = 0;j < n;j ++){
                str += ".";
            }
            item.add(str);
        }
        generate(0,n,item,result,mark);
        return result;
    }

    /**
     * 递归，回溯
     * @param k：完成了第k行的皇后放置
     * @param n
     * @param item
     * @param result
     * @param mark
     */
    private void generate(int k, int n, List<String> item, List<List<String>> result, int[][] mark) {
        if (k == n){
            result.add(new ArrayList<>(item));
            return;
        }
        //从第一列开始尝试
        for (int i =0;i < n;i ++){
            //放置皇后，更新棋盘，同步更新解
            if (mark[k][i] == 0){
                //记录当前mark棋盘
                int[][] temp_mark = new int[n][n];
                //二维数组的深拷贝
                for (int j = 0;j < n;j ++){
                    System.arraycopy(mark[j],0,temp_mark[j],0,n);
                }
                //放置皇后，更新棋盘
                put_down_the_queen(k,i,n,mark);
                //同步更新解
                StringBuilder str = new StringBuilder(item.get(k));
                str.setCharAt(i,'Q');
                item.set(k,str.toString());
                //递归放置第k+1行
                generate(k + 1,n,item,result,mark);
                //函数执行完，发生回溯，不进行在该列放置皇后的操作
                mark = temp_mark;
                str.setCharAt(i,'.');
                item.set(k,str.toString());
            }
        }
    }

    /**
     * 把皇后放到棋盘上，更新棋盘
     * @param x
     * @param y
     * @param n
     * @param mark
     */
    private void put_down_the_queen(int x, int y, int n, int[][] mark) {
        mark[x][y] = 1;
        for (int i = 1;i < n;i ++){
            //每个方向向外延伸N-1次，为了方便，每个方向都向外延伸最远的情况，有8个方向
            for (int j = 0;j < 8;j ++){
                int new_x = x + i * dx[j];
                int new_y = y + i * dy[j];
                if (new_x >= 0 && new_x < n && new_y >= 0 && new_y < n){
                    mark[new_x][new_y] = 1;
                }
            }
        }
    }
}
