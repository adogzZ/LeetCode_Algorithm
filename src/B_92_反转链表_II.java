public class B_92_反转链表_II {
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
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head,int m,int n){
        //反转链表的长度
        int len = (n - m + 1);
        ListNode pre_head = null;
        ListNode result = head;
        //找到与反转链表的头结点相连接的节点
        while (head != null && --m > 0){
            pre_head = head;
            head = head.next;
        }
        //反转链表的尾结点
        ListNode rear = head;
        ListNode new_head = null;
        //新链表，即反转链表，new_head为反转链表的头节点
        while (head != null && len > 0){
            ListNode next = head.next;
            head.next = new_head;
            new_head = head;
            head = next;
            len --;
        }
        //链接反转链表表尾
        rear.next = head;
        //链接反转链表表头
        if (pre_head != null){
            pre_head.next = new_head;
        }else {
            result = new_head;
        }
        return result;
    }

    /**
     * 递归，回溯
     * @param right
     * @param m
     * @param n
     */
    private boolean stop;
    private ListNode left;

    public void recurse(ListNode right,int m,int n){
        //递归出口
        if (n == 1){
            return;
        }
        //递归移动right指针
        right = right.next;
        //递归移动left指针
        if (m > 1){
            this.left = this.left.next;
        }
        recurse(right,m-1,n-1);
        //回溯
        //停止交换
        if (this.left == right || right.next == this.left){
            this.stop = true;
        }
        //交换
        if (!this.stop){
            int exc = this.left.val;
            this.left.val = right.val;
            right.val = exc;

            //移动left指针，right不需要移动，因为回溯的过程中记录着right指针的状态
            this.left = this.left.next;
        }
    }

    public ListNode reverseBetween_(ListNode head,int m,int n){
        this.left = head;
        this.stop = false;
        recurse(head,m,n);
        return head;
    }
}
