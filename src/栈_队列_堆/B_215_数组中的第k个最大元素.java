package 栈_队列_堆;

import java.util.PriorityQueue;

public class B_215_数组中的第k个最大元素 {
    /**
     * 维护一个只有k个元素的最小堆，堆中元素个数小于k个时，直接入堆，否则跟堆顶元素比较，
     * 比堆顶元素大的才能入堆。这样遍历完所有元素，堆顶元素即为第k个最大元素。相当于前k大
     * 的元素都在最小堆中。
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums,int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        //遍历数组元素
        for (int i = 0;i < nums.length;i ++){
            //前k个元素直接入堆
            if (i < k){
                queue.add(nums[i]);
            }else {
                if (nums[i] > queue.peek()){
                    queue.remove();
                    queue.add(nums[i]);
                }
            }
        }
        return queue.peek();
    }
}
