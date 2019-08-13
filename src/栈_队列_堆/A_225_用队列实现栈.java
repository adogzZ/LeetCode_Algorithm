package 栈_队列_堆;

import java.util.LinkedList;

public class A_225_用队列实现栈 {

    private LinkedList<Integer> queue = new LinkedList<>();
    /**
     *思想：push到队列的元素，按照先入队的元素存储在队列的队尾，
     *后入队的元素存储在队列的队头，即把按顺序进入队列的元素逆序存储在队列中
     *出栈：就是弹出队头元素
     *返回栈顶元素：返回队头元素
     *栈空：判断队空
     */
    //初始化栈
    public A_225_用队列实现栈() {

    }
    //入栈
    //每插入一个元素，就把该元素之前的队头元素依次插入到队尾，同时删除队头元素，实现逆序
    public void push(int x){
        queue.push(x);
        int len = queue.size();
        while (len > 1){
            queue.push(queue.remove());
            len --;
        }
    }
    //出栈
    public int pop(){
        int x = queue.remove();
        return x;
    }
    //返回栈头元素
    public int top(){
        return queue.peek();
    }
    //判断栈空
    public boolean empty(){
        return queue.isEmpty();
    }
}
