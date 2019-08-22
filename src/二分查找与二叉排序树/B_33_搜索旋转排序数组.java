package 二分查找与二叉排序树;

public class B_33_搜索旋转排序数组 {

    /**
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums,int target){
        int begin = 0;
        int end = nums.length - 1;
        while (begin <= end){
            int mid = (begin + end) / 2;
            if (target == nums[mid]){
                return mid;
            }else if (target < nums[mid]){
                if (nums[begin] < nums[mid]){
                    if (target >= nums[begin]){
                        end = mid - 1;
                    }else {
                        begin = mid + 1;
                    }
                }else if (nums[begin] > nums[mid]){
                    end = mid - 1;
                }else if (nums[begin] == nums[mid]){
                    begin = mid + 1;
                }
            }else if (target > nums[mid]){
                if (nums[begin] < nums[mid]){
                    begin = mid + 1;
                }else if (nums[begin] > nums[mid]){
                    if (target < nums[begin]){
                        begin = mid + 1;
                    }else {
                        end = mid - 1;
                    }
                }else if (nums[begin] == nums[mid]){
                    begin = mid + 1;
                }
            }
        }
        return -1;
    }
}
