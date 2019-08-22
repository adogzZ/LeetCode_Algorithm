package 二分查找与二叉排序树;

public class B_449_序列化和反序列化二叉搜索树 {

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
     * 按前序遍历的顺序构造的二叉搜索树跟原先的二叉搜索树完全相同
     * 二叉搜索树序列化为字符串
     * @param root
     * @return
     */
    public String serialize(TreeNode root){
        String data = "";
        data = preOrder(root,data);
        return data;
    }

    /**
     * 前序遍历二叉搜索树，编码成字符串
     * @param node
     * @param s
     * @return
     */
    private String preOrder(TreeNode node, String s) {
        if (node == null){
            return s;
        }
        s += change_int_to_string(node.val,"");
        s = preOrder(node.left,s);
        s = preOrder(node.right,s);
        return s;
    }



    /**
     * 把数字转成字符串
     * @param val
     * @param s
     * @return
     */
    private String change_int_to_string(int val, String s) {
        String temp = "";
        while (val > 0){
            temp += val % 10;
            val /= 10;
        }
        for (int i = temp.length() - 1;i >= 0;i --){
            s += temp.charAt(i);
        }
        s += '#';
        return s;
    }

    /**
     * 按前序遍历的顺序构造的二叉搜索树跟原先的二叉搜索树完全相同
     * 字符串反序列化为二叉搜索树
     * @param data
     * @return
     */
    public TreeNode deserialize(String data){
        if (data.length() == 0){
            return null;
        }
        int val = 0;
        int i = 0;
        //找到根节点
        while (data.charAt(i) != '#'){
            val = val * 10 + data.charAt(i) - '0';
            i ++;
        }
        TreeNode root = new TreeNode(val);
        val = 0;
        for (i = i+1;i < data.length();i ++){
            if (data.charAt(i) == '#'){
                BST_insert(root,new TreeNode(val));
                val = 0;
            }else {
                val = val * 10 + data.charAt(i) - '0';
            }
        }
        return root;
    }

    /**
     * 二叉搜索树的插入
     * @param node
     * @param insert_node
     */
    private void BST_insert(TreeNode node, TreeNode insert_node) {
        if (insert_node.val < node.val){
            if (node.left != null){
                BST_insert(node.left,insert_node);
            }else {
                node.left = insert_node;
            }
        }else {
            if (node.right != null){
                BST_insert(node.right,insert_node);
            }else {
                node.right = insert_node;
            }
        }
    }
}
