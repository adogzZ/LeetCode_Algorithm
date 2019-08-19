package 递归_回溯_分治;

import java.util.ArrayList;
import java.util.List;

public class B_78_子集 {

    /**
     * 回溯算法
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums){
        //存储最终结果
        List<List<Integer>> result = new ArrayList<>();
        //子集
        List<Integer> item = new ArrayList<>();
        //添加空集
        result.add(item);
        generate(0,nums,item,result);
        return result;
    }

    /**
     * 递归生成子集
     * @param i
     * @param nums
     * @param item
     * @param result
     */
    public void generate(int i,int[] nums,List<Integer> item,List<List<Integer>> result){
        //没有更多元素可以递归
        if (i >= nums.length){
            return;
        }
        //添加该元素
        item.add(nums[i]);
        //添加到结果集
        result.add(new ArrayList<>(item));
        //递归添加下一个元素
        generate(i+1,nums,new ArrayList<>(item),result);
        //回溯，不添加该元素
        item.remove(item.size() - 1);
        //递归添加下一个元素
        generate(i+1,nums,new ArrayList<>(item),result);
    }

    /**
     * 位运算法，用0表示某一元素不出现，1表示出现
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        //所有集合的个数
        int all_set = 1 << nums.length;
        //遍历所有用二进制表示的集合
        for (int i = 0;i < all_set;i ++){
            //子集
            List<Integer> item = new ArrayList<>();
            //遍历所有元素，与二进制表示的集合相与为1
            for (int j = 0;j < nums.length;j ++){
                if ((i & (1 << j)) > 0){
                    item.add(nums[j]);
                }
            }
            result.add(item);
        }
        return result;
    }
}
