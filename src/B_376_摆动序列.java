public class B_376_摆动序列 {

    /**
     * 最长摆动子序列
     * 把所有元素通过折线连接起来就可以看出，摆动序列的元素一定都是在折线的转折点上，
     * 即连续的递增或递减序列的首尾元素，这样更可能使得尾部的后一个元素成为摆动子序列
     * 的下一个元素。
     * 状态机转换的思想
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums){
        //少于两个元素的序列是摆动序列
        if (nums.length < 2){
            return nums.length;
        }
        final int BEGIN = 0;
        //上升状态
        final int UP = 1;
        //下降状态
        final int DOWN = 2;
        //开始状态
        int STATE = BEGIN;
        //当序列长度大于等于2时，最长摆动子序列长度从1开始
        int max_length = 1;
        //遍历整个序列
        for (int i = 1;i < nums.length;i ++){
            switch (STATE){
                case BEGIN:
                    //上升状态
                    if (nums[i] > nums[i - 1]){
                        STATE = UP;
                        //当状态发生变化时，摆动子序列的长度加1
                        max_length ++;
                    }else if (nums[i] < nums[i - 1]){
                        STATE = DOWN;
                        max_length ++;
                    }
                    break;
                case UP:
                    //当状态发生变化时，摆动子序列的长度加1
                    if (nums[i] < nums[i - 1]){
                        STATE = DOWN;
                        max_length ++;
                    }
                    break;
                case DOWN:
                    if (nums[i] > nums[i - 1]){
                        STATE = UP;
                        max_length ++;
                    }
                    break;
            }
        }
        return max_length;
    }
}
