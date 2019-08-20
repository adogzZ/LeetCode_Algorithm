package 二叉树与图;

public class B_114_二叉树转链表 {

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
     * 每个节点的右子树需要连接到该节点的左子树，而左子树的最后一个节点需要连接到当前节点的右
     * 子树上，每次遍历左右子树时，需要把左右子树的最后一个节点传出来。
     * @param root
     */
    public void flatten(TreeNode root){
        preOrder(root);
    }

    /**
     * 后序遍历二叉树
     * @param node
     * @return
     */
    private TreeNode preOrder(TreeNode node) {
        if (node == null){
            return null;
        }
        if (node.left == null && node.right == null){
            return node;
        }
        //备份左右指针
        TreeNode left = node.left;
        TreeNode right = node.right;
        //左子树被拉伸后的最后一个节点
        TreeNode left_last = null;
        //右子树被拉伸后的最后一个节点
        TreeNode right_last = null;
        //当前子树的最后一个节点
        TreeNode last = null;
        //先拉伸左子树
        left_last = preOrder(left);
        //再拉伸右子树
        right_last = preOrder(right);
        //当前子树的拉伸操作
        node.left = null;
        if (left != null){
            node.right = left;
        }
        if (left_last != null){
            left_last.right = right;
        }
        //如果右子树不为空，当前子树的最后一个节点为右子树的最后一个节点，否则为左子树的最后一个节点
        if (right_last != null){
            last = right_last;
        }else {
            last = left_last;
        }
        return last;
    }
}
