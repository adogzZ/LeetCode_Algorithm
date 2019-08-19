package 递归_回溯_分治;

import java.util.*;

public class B_40_组合总和II {
    /**
     * 递归选择数组中的元素，若发现所选元素的和已经大于target值，就应该剪枝，不再继续递归
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates,int target){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(candidates);
        generate(0,candidates,item,result,set,0,target);
        return result;
    }

    /**
     * 递归，回溯，剪枝
     * @param i
     * @param candidates
     * @param item
     * @param result
     * @param set
     * @param sum
     * @param target
     */
    private void generate(int i, int[] candidates, List<Integer> item, List<List<Integer>> result, Set<List<Integer>> set,int sum,int target) {
        //递归退出的接口，剪枝的条件
        if (i >= candidates.length || sum > target){
            return;
        }
        List<Integer> k;
        sum += candidates[i];
        item.add(candidates[i]);
        if (!set.contains(k=new ArrayList<>(item)) && sum == target){
            result.add(k);
            set.add(k);
        }
        generate(i+1,candidates,k,result,set,sum,target);
        //回溯
        sum -= candidates[i];
        item.remove(item.size() - 1);
        k = new ArrayList<>(item);
        generate(i+1,candidates,k,result,set,sum,target);
    }
}
