
public class B_142_环形链表 {
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
     * 设置两个指针，快指针和慢指针，慢指针走一步，快指针走两步，假设有环，开始时
     * 慢指针走到环的入口，距离为F，快指针走了2F。设环的长度为C，此时快指针在F余C处，假设是h
     * 然后慢指针走C-h，快指针走2(C-h)与慢指针相遇，若无环就不会相遇。假设C-h为a，剩余的为b
     * 所以F = b
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        //相遇的节点
        ListNode meet = null;
        while (fast != null){
            //快指针走两步，慢指针走一步
//            fast = fast.next.next;//有可能出现空指针，所以走一步就要判断一次
            fast = fast.next;
            slow = slow.next;
            if (fast == null){
                return null;
            }
            fast = fast.next;
            if (fast == slow){
                meet = fast;
                break;
            }
        }
        while (head != null && meet != null){
            if (head == meet){
                return head;
            }
            head = head.next;
            meet = meet.next;
        }
        return null;
    }
}
