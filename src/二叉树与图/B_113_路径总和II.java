package 二叉树与图;

import java.util.ArrayList;
import java.util.List;

public class B_113_路径总和II {

    /**
     * 二叉树节点
     */
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 先序遍历二叉树，遍历过程中计算路径和，当路径和等于给定目标和时，把路径记录到结果集中
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root,int sum){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        //路径和
        int path_sum = 0;
        preOrder(root,item,result,path_sum,sum);
        return result;
    }

    /**
     * 先序遍历二叉树
     * @param node
     * @param item
     * @param result
     * @param path_sum
     * @param sum
     */
    private void preOrder(TreeNode node, List<Integer> item, List<List<Integer>> result, int path_sum, int sum) {
        if (node == null){
            return;
        }
        //先序遍历
        path_sum += node.val;
        item.add(node.val);
        if (path_sum == sum && node.left == null && node.right == null){
            result.add(new ArrayList<>(item));
        }
        preOrder(node.left,item,result,path_sum,sum);
        preOrder(node.right,item,result,path_sum,sum);
        path_sum -= node.val;
        item.remove(item.size() - 1);
    }
}
