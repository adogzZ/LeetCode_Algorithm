package 链表;

public class B_86_分隔链表 {
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
     * 定义两个哑节点（临时头结点）before和after，遍历链表，小于x的节点
     * 连接到before中，大于或者等于x的节点链接到after中，最后链接两个链表，在连接链表时
     * 要注意哑节点不能作为链表的一部分。
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head,int x){
        ListNode before = new ListNode(0);
        ListNode after = new ListNode(0);
        ListNode before_ptr = before;
        ListNode after_ptr = after;
        while (head != null){
            //小于x的节点插入到before后面
            if (head.val < x){
                before_ptr.next = head;
                before_ptr = before_ptr.next;
            }else {
                //大于或等于x的节点插入到after后面
                after_ptr.next = head;
                after_ptr = after_ptr.next;
            }
            head = head.next;
        }
        //链接链表的两部分
        before_ptr.next = after.next;
        //链表的表尾置空
        after_ptr.next = null;
        return before.next;
    }
}
