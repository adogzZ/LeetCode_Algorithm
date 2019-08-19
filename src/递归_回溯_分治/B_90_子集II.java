package 递归_回溯_分治;

import javax.swing.*;
import java.util.*;

public class B_90_子集II {
    /**
     * hashSet的contains方法是判断元素的hashcode，如果hashcode相同就返回true，而arrayList的
     * hashcode是根据List<object>的object对象的hashcode通过一定计算累加得到的。所以这里可以根
     * 据arrayList元素（顺序、数值）是否相同来判断hashSet中是否包含相同元素。
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //最终的结果集
        List<List<Integer>> result = new ArrayList<>();
        //子集
        List<Integer> item = new ArrayList<>();
        //用set对子集去重
        Set<List<Integer>> set = new HashSet<>();
        //对数组排序，保证相同子集中元素的顺序是相同的
        Arrays.sort(nums);
        //添加空集
        result.add(item);
        generate(0,nums,item,result,set);
        return result;
    }

    /**
     * 递归，回溯
     * @param i
     * @param nums
     * @param item
     * @param result
     * @param set
     */
    public void generate(int i, int[] nums, List<Integer> item, List<List<Integer>> result, Set<List<Integer>> set){
        if (i >= nums.length){
            return;
        }
        item.add(nums[i]);
        List<Integer> k;
        //去重
        if (!set.contains(k = new ArrayList<>(item))){
            result.add(k);
            set.add(k);
        }
        generate(i+1,nums,k,result,set);
        //回溯
        item.remove(item.size() - 1);
        k = new ArrayList<>(item);
        generate(i+1,nums,k,result,set);
    }
}
