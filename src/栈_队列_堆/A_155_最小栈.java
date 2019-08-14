package 栈_队列_堆;

import java.util.Stack;

public class A_155_最小栈 {

    private Stack<Integer> data_stack;
    private Stack<Integer> helper_stack;

    /**
     * 利用一个辅助栈，存储数据栈的各个状态的最小值
     */
    public A_155_最小栈() {
        data_stack = new Stack<>();
        helper_stack = new Stack<>();
    }

    public void push(int x){
        //数据栈跟辅助栈是同步的
        data_stack.push(x);
        //辅助栈为空时，直接入栈
        if (helper_stack.empty()){
            helper_stack.push(x);
        }else {
            //如果辅助栈不为空，辅助栈栈顶数据此时记录的是数据栈上一个状态的最小值
            if (helper_stack.peek() > x){
                helper_stack.push(x);
            }else {
                helper_stack.push(helper_stack.peek());
            }
        }
    }

    public void pop(){
        data_stack.pop();
        helper_stack.pop();
    }

    public int top(){
        return data_stack.peek();
    }

    public int getMin(){
        return helper_stack.peek();
    }
}
