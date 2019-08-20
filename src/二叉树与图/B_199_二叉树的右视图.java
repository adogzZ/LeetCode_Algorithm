package 二叉树与图;

import java.util.*;

public class B_199_二叉树的右视图 {

    /**
     * 二叉树节点
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 二叉树节点和所在层数的映射
     */
    public class Map{
        TreeNode node;
        int depth;

        public Map(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }


    /**
     * 二叉树的右视图就是每一层的最后一个节点值的集合，让view[i]表示第i层的最后一个元素的值
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root){
        List<Integer> view = new ArrayList<>();
        Queue<Map> queue = new LinkedList<>();
        if (root != null){
            queue.add(new Map(root,0));
        }
        //二叉树的宽度优先搜索
        while (!queue.isEmpty()){
            TreeNode node = queue.peek().node;
            int depth = queue.peek().depth;
            queue.remove();
            if (depth == view.size()){
                view.add(node.val);
            }else {
                view.set(depth,node.val);
            }
            if (node.left != null){
                queue.add(new Map(node.left,depth + 1));
            }
            if (node.right != null){
                queue.add(new Map(node.right,depth + 1));
            }
        }
        return view;
    }
}
