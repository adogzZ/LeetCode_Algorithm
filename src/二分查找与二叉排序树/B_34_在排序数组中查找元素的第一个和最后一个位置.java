package 二分查找与二叉排序树;

public class B_34_在排序数组中查找元素的第一个和最后一个位置 {

    /**
     * 用二分查找查找target的左端点和右端点
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums,int target){
        int[] result = new int[2];
        //查找左端点
        result[0] = left_bound(nums,target);
        //查找右端点
        result[1] = right_bound(nums,target);
        return result;
    }

    /**
     * 查找左端点
     * @param nums
     * @param target
     * @return
     */
    private int left_bound(int[] nums, int target) {
        int begin = 0;
        int end = nums.length - 1;
        //二分查找左端点
        while (begin <= end){
            int mid = (begin + end) / 2;
            if (target == nums[mid]){
                //找到左端点
                if (mid == 0 || target > nums[mid-1]){
                    return mid;
                }
                //查找左端点
                end = mid - 1;
            }else if (target < nums[mid]){
                end = mid - 1;
            }else if (target > nums[mid]){
                begin = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找右端点
     * @param nums
     * @param target
     * @return
     */
    private int right_bound(int[] nums, int target) {
        int begin = 0;
        int end = nums.length - 1;
        //二分查找右端点
        while (begin <= end){
            int mid = (begin + end) / 2;
            if (target == nums[mid]){
                //找到右端点
                if (mid == nums.length - 1 || target < nums[mid+1]){
                    return mid;
                }
                //查找右端点
                begin = mid + 1;
            }else if (target < nums[mid]){
                end = mid - 1;
            }else if (target > nums[mid]){
                begin = mid + 1;
            }
        }
        return -1;
    }
}
