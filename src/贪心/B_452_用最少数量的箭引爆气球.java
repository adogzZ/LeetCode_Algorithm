package 贪心;

import java.util.Arrays;
import java.util.Comparator;

public class B_452_用最少数量的箭引爆气球 {

    //对气球（长度为2的int型数组），按左端点从小到大排序
    Comparator<int[]> comparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    };
    /**
     * 对气球按左端点从小到大排序，遍历所有气球，同时维护一个射击区间，当发现此时
     * 该气球不在射击区间内，就增加一支弓箭，同时维护一个新的射击区间，直到遍历完所有的气球。
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points){
        if (points.length == 0){
            return 0;
        }
        //排序
        Arrays.sort(points,comparator);
        //引爆所有气球所需最少的弓箭数量
        int shot_num = 1;
        //射击区间，初始时为第一个气球的区间
        int shot_begin = points[0][0];
        int shot_end = points[0][1];
        //遍历所有气球
        for (int i = 0;i < points.length;i ++){
            //当气球左端点在射击区间内，
            if (points[i][0] <= shot_end){
                shot_begin = points[i][0];
                //当气球右端点也在射击区间内，
                if (points[i][1] < shot_end){
                    shot_end = points[i][1];
                }
            }else {
                //气球不在射击区间内
                shot_num ++;
                //维护新的射击区间
                shot_begin = points[i][0];
                shot_end = points[i][1];
            }
        }

        return shot_num;
    }
}
