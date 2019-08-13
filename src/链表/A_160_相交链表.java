package 链表;

public class A_160_相交链表 {
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
     * 设置两个指针，初始时指向各自的头部，计算两个链表的长度，让链表从后端对齐，
     * 较长链表的指针向后移动len_long-len_short个节点，然后两个指针同时向后移动
     * 直到两个指针相同，返回该指针
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA,ListNode headB){
        int len_headA = get_len(headA);
        int len_headB = get_len(headB);
        //如果链表A较长，就移动链表A的指针
        if (len_headA > len_headB){
            headA = move(len_headA,len_headB,headA);
        }
        //如果链表B较长，就移动链表B的指针
        if (len_headA < len_headB){
            headB = move(len_headB,len_headA,headB);
        }
        while (headA != null && headB != null){
            //相交，就返回
            if (headA == headB){
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        //没有相交，就返回null
        return null;
    }

    /**
     * 计算链表长度
     * @param head
     * @return
     */
    public int get_len(ListNode head){
        int len = 0;
        while (head != null){
            head = head.next;
            len ++;
        }
        return len;
    }

    /**
     * 移动较长链表的指针len_long-len_short个节点
     * @param len_long
     * @param len_short
     * @param head
     * @return
     */
    public ListNode move(int len_long,int len_short,ListNode head){
        int x = len_long - len_short;
        while (x > 0){
            head = head.next;
            x --;
        }
        return head;
    }
}
