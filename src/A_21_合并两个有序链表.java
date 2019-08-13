public class A_21_合并两个有序链表 {
    /**
     * 链表节点
     */
    class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 迭代
     * 定义一个哑节点（临时节点），迭代遍历两个链表l1，l2，如果l1<l2，把l1链接到临时节点后，向后移
     * 动l1，如果l1>l2，把l2链接到临时节点后，向后移动l2。最后，如果l1为null，把l2剩余节点链接到
     * 链表的尾部，如果l2为null，就把l1剩余节点链接到链表的尾部。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1,ListNode l2){
        //定义临时节点
        ListNode temp_node = new ListNode(0);
        ListNode pre = temp_node;
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                pre.next = l1;
                l1 = l1.next;
            }else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        if (l1 == null){
            pre.next = l2;
        }
        if (l2 == null){
            pre.next = l1;
        }
        return temp_node.next;
    }
}
