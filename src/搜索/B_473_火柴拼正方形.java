package 搜索;


import java.util.Arrays;

public class B_473_火柴拼正方形 {

    /**
     * @param nums
     * @return
     */
    public boolean makesquare(int[] nums){
        if (nums.length < 4){
            return false;
        }
        //正方形的周长
        int sum = 0;
        for (int i = 0;i < nums.length;i ++){
            sum += nums[i];
        }
        //周长一定是4的整数倍
        if (sum % 4 != 0){
            return false;
        }
        Arrays.sort(nums);
        //对数组按从大到小排序，从前往后搜索时，对于更长的火柴，可以选择的摆放位置更少
        int[] temp = new int[nums.length];
        for (int i = nums.length - 1,j = 0;i >= 0;i --,j ++){
            temp[j] = nums[i];
        }
        //火柴的4个摆放位置
        int[] bucket = new int[4];
        return DFS_search(0,temp,sum / 4,bucket);
    }

    /**
     * @param index
     * @param temp
     * @param side
     * @param bucket
     * @return
     */
    private boolean DFS_search(int index, int[] temp, int side, int[] bucket) {
        //所有火柴都遍历完了，检查四条边
        if (index == temp.length){
            return bucket[0] == side && bucket[1] == side && bucket[2] == side && bucket[3] == side;
        }
        //检查四条边，判断火柴可以放到哪条边
        for (int i = 0;i < 4;i ++){
            if (temp[index] + bucket[i] > side){
                continue;
            }
            //把火柴放入
            bucket[i] += temp[index];
            //递归放入下一根火柴时判断，是否下根火柴能否摆放
            if (DFS_search(index + 1,temp,side,bucket)){
                return true;
            }
            //如果不可以就回溯
            bucket[i] -= temp[index];
        }
        return false;
    }
}
