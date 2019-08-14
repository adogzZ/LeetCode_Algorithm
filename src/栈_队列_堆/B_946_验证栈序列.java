package 栈_队列_堆;

import java.util.Stack;

public class B_946_验证栈序列 {

    /**
     * 模拟入栈出栈的操作：按顺序入栈，每次入栈后检查栈顶元素是否应该出栈，判断是否应该出栈
     * 就是根据给定的出栈序列来判断
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed,int[] popped){
        //模拟栈
        Stack<Integer> stack = new Stack<>();
        int len = pushed.length;
        //记录出栈元素
        int j = 0;
        //每个元素按顺序入栈
        for (int i = 0;i < len;i ++){
            stack.push(pushed[i]);
            //判断栈顶元素是否应该出栈
            while (!stack.isEmpty() && stack.peek() == popped[j]){
                stack.pop();
                j ++;
            }
        }

        //栈中元素全部按出栈序列出栈
        if (stack.isEmpty()){
            return true;
        }
        return false;
    }
}
