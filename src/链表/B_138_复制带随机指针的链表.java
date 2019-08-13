package 链表;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class B_138_复制带随机指针的链表 {
    /**
     * 带随机指针的链表节点
     */
    class Node{
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int val, Node next, Node random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }

    /**
     * 利用map保存源链表的地址与节点id（节点之间的逻辑关系）之间的映射关系
     * 利用list列表保存新链表节点之间的逻辑关系（通过list下标）
     * @param head
     * @return
     */
    public Node copyRandomList(Node head){
        Map<Node,Integer> origin_map = new HashMap<>();
        List<Node> new_list = new ArrayList<>();
        Node ptr = head;
        int i = 0;
        //遍历原链表
        while (ptr != null){
            //构建新链表和原链表的逻辑关系
            origin_map.put(ptr,i);
            Node node = new Node(ptr.val, null, null);
            new_list.add(node);
            ptr = ptr.next;
            i ++;
        }
        //新链表最后一个节点的next指针，指向null
        new_list.add(null);
        //重新遍历原链表
        ptr = head;
        //遍历新链表
        i = 0;
        while (ptr != null){
            //新链表的next指针指向
            new_list.get(i).next = new_list.get(i+1);
            //新链表的random指针指向
            if (ptr.random != null){
                new_list.get(i).random = new_list.get(origin_map.get(ptr.random));
            }
            ptr = ptr.next;
            i ++;
        }
        return new_list.get(0);
    }
}
