package 栈_队列_堆;

import java.util.Stack;

public class A_232_用栈实现队列 {

    private Stack<Integer> data_stack = new Stack<>();
    //用来辅助实现逆序存储的临时栈
    private Stack<Integer> temp_stack = new Stack<>();

    /**
     *利用两个栈，实现元素的逆序存储
     */

    //初始化队列
    public A_232_用栈实现队列() {

    }
    //入队
    public void push(int x){
        while (!data_stack.empty()){
            temp_stack.push(data_stack.pop());
        }
        temp_stack.push(x);
        while (!temp_stack.empty()){
            data_stack.push(temp_stack.pop());
        }
    }
    //出队
    public int pop(){
        return data_stack.pop();
    }
    //返回队头元素
    public int peek(){
        return data_stack.peek();
    }
    //队列是否为空
    public boolean empty(){
        return data_stack.isEmpty();
    }
}
