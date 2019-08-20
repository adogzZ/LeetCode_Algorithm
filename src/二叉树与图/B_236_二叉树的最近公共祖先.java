package 二叉树与图;

import java.util.ArrayList;
import java.util.List;

public class B_236_二叉树的最近公共祖先 {
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

    //finish表示是否找到了节点，把finish定义成全局变量，否则，当找到指定节点发生回溯时finish又会变为0；
    int finish = 0;

    /**
     * 深度优先搜索二叉树，记录从根节点到两个指定节点的节点路径，两个指定节点的节点路径中
     * 最后出现的相同的节点即为最近公共祖先。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
        //从根节点到p，q俩节点的节点路径
        List<TreeNode> p_path = new ArrayList<>();
        List<TreeNode> q_path = new ArrayList<>();

        //前序遍历二叉树，记录到p节点的节点路径
        preOrder(root,p,p_path);
        finish = 0;
        //前序遍历二叉树，记录到q节点的节点路径
        preOrder(root,q,q_path);

        int path_len = 0;
        if (p_path.size() < q_path.size()){
            path_len = p_path.size();
        }else {
            path_len = q_path.size();
        }
        //最近公共祖先
        TreeNode result = new TreeNode(-1);
        //同时遍历p，q俩节点的节点路径
        for (int i = 0;i < path_len;i ++){
           if (p_path.get(i) == q_path.get(i)){
               result = p_path.get(i);
           }
        }
        return result;
    }

    /**
     * 从根节点到某给定节点的路径
     * @param node
     * @param search
     * @param path
     */
    private void preOrder(TreeNode node, TreeNode search, List<TreeNode> path) {
        if (node == null || finish == 1){
            return;
        }
        path.add(node);
        //找到search节点
        if (node.val == search.val){
            finish = 1;
        }
        preOrder(node.left,search,path);
        preOrder(node.right,search,path);
        //当找到了节点，不要修改path
        if (finish == 0){
            path.remove(path.size() - 1);
        }
    }
}