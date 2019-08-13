public class C_23_合并k个排序链表 {

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
     * 分治
     * 递归合并前k/2个链表，递归合并后k/2个链表，最后合并已经递归合并完成的第一个子链表和第二个子链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists){
        //判断几种特殊情况
        if (lists.length == 0){
            return null;
        }
        if (lists.length == 1){
            return lists[0];
        }
        if (lists.length == 2){
            return mergeTwoLists(lists[0],lists[1]);
        }
        //利用分治的思想合并lists中的k个链表
        int mid = lists.length/2;
        //把lists分成两部分
        ListNode[] lists1 = new ListNode[mid];
        ListNode[] lists2 = new ListNode[lists.length - mid];
        //把lists中的前半部分链表头结点放到list1中
        for (int i = 0;i < mid;i ++){
            lists1[i] = lists[i];
        }
        //把lists中的后半部分链表头结点放到list2中
        for (int i = mid;i < lists.length;i ++){
            lists2[i - mid] = lists[i];
        }
        //递归合并前半段
        ListNode l1 = mergeKLists(lists1);
        //递归合并后半段
        ListNode l2 = mergeKLists(lists2);
        return mergeTwoLists(l1,l2);
    }

    /**
     *合并两个有序链表
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
