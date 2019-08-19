package 贪心;

public class C_45_跳跃游戏II {
    /**
     * 假设就跳一步，跳至初始位置所能跳达的最远位置（然后遍历所有位置，遍历的过程中记录在
     * 这之前所能跳达的最远位置）发现这个最远位置未能达到终点，那么要想到达终点，就必须在
     * 这之前跳到更远的位置，然后跳跃数加一，记录这个更远的位置。重复之前的操作。
     * @param nums
     * @return
     */
    public int jump(int[] nums){
        //不需要跳跃
        if (nums.length < 2){
            return 0;
        }
        //当前位置所能跳达的最远位置，初始时为nums[0]
        int curr_max = nums[0];
        //当前位置之前所能跳达的最远位置
        int pre_max = nums[0];
        //最小跳跃次数
        int jump_min = 1;
        //遍历所有位置
        for (int i = 1;i < nums.length;i ++){
            //如果未能达到终点，必须在这之前跳至更远的位置
            if (i > curr_max){
                jump_min ++;
                curr_max = pre_max;
            }
            //记录当前位置之前所能跳达的最远位置
            if (pre_max < nums[i] + i){
                pre_max = nums[i] + i;
            }
        }
        return jump_min;
    }
}
