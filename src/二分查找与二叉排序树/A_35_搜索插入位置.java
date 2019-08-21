package 二分查找与二叉排序树;

public class A_35_搜索插入位置 {
    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums,int target){
        //要返回的索引
        int index = -1;
        int begin = 0;
        int end = nums.length - 1;
        //没有找到正确位置，就继续查找
        while (index == -1){
            int mid = (begin + end) / 2;
            if (target == nums[mid]){
                index = mid;
            }else if (target < nums[mid]){
                if (mid == 0 || target > nums[mid-1]){
                    index = mid;
                }
                end = mid - 1;
            }else if (target > nums[mid]){
                if (mid == nums.length - 1 || target < nums[mid+1]){
                    index = mid + 1;
                }
                begin = mid + 1;
            }
        }
        return index;
    }
}
