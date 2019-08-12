public class A_206_反转列表 {
    /**
     * 链表节点
     */
    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 迭代
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head){
        //要实现链表的反转，需在遍历链表时修改当前节点的指针指向上一个节点，所以必须事前保存下一个节点，否则会找不到下一个节点
        ListNode preNode = null;
        ListNode currNode = head;
        while (currNode != null){
            ListNode nextNode = currNode.next;
            currNode.next = preNode;
            preNode = currNode;
            currNode = nextNode;
        }
        return preNode;
    }

    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList_(ListNode head){
        //找到退出条件
        if (head == null || head.next == null){
            return head;
        }
        //假设当前节点后面的子链都已经反转，现在要反转当前节点，next表示后面子链表的头指针，节点状态（参数）都保存在函数栈当中
        ListNode next = reverseList_(head.next);
        head.next.next = head;
        head.next = null;
        return next;
    }
}
