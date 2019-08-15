public class B_55_跳跃游戏 {

    public boolean canJump(int[] nums){
        //每个位置最远可跳达的位置
        int[] index = new int[nums.length];
        //初始化index数组
        for (int i = 0; i < nums.length;i ++){
            index[i] = nums[i] + i;
        }
        //当前的跳跃位置
        int jump = 0;
        //从开始位置到当前跳跃位置之间的所有跳跃位置可跳达的最远位置
        int max_index = index[0];
        //当跳达终点位置或者不能再往前跳，即当前跳跃位置超过了最远可跳达位置时停止跳跃
        while (jump < index.length && jump <= max_index){
            //如果可跳达更远的位置，就更新最远可跳达位置
            if (max_index < index[jump]){
                max_index = index[jump];
            }
            //遍历所有位置
            jump ++;
        }

        if (jump == index.length){
            return true;
        }
        return false;
    }
}
