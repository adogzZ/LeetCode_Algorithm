package 栈_队列_堆;

import java.util.Stack;

public class C_224_基本计算器 {

    /**
     * 字符串处理思路：有限状态自动机的思想进行状态转换，在每个状态下进行if/else判断
     * 使用栈处理优先级：
     * @param s
     * @return
     */
    public int calculate(String s){
        //初始时有三种状态：开始状态，数字状态，操作符状态
        final int STATE_BEGIN = 0;
        //数字状态：处理数字
        final int NUMBER_STATE = 1;
        final int OPERATION_STATE = 2;

        Stack<Integer> data_stack = new Stack<>();
        Stack<Character> operation_stack = new Stack<>();

        int number = 0;
        //初始状态
        int STATE = STATE_BEGIN;
        int compute_flag = 0;

        //遍历字符串
        for (int i = 0;i < s.length();i ++){
            if (s.charAt(i) == ' '){
                continue;
            }
            switch (STATE){
            //开始状态，判断是要处理数字还是要进行计算
            case STATE_BEGIN:
                if (s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    STATE = NUMBER_STATE;
                }else {
                    STATE = OPERATION_STATE;
                }
                i --;
                break;
            case NUMBER_STATE:
                if (s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    number = number * 10 + s.charAt(i) - '0';
                }else {
                    data_stack.push(number);
                    //如果此时可以计算，则计算栈内的值
                    if (compute_flag == 1){
                        compute(data_stack,operation_stack);
                    }
                    number = 0;
                    i --;
                    STATE = OPERATION_STATE;
                }
                break;
            case OPERATION_STATE:
                if (s.charAt(i) == '+' || s.charAt(i) == '-'){
                    operation_stack.push(s.charAt(i));
                    compute_flag = 1;
                }else if (s.charAt(i) == '('){
                    STATE = NUMBER_STATE;
                    compute_flag = 0;
                }else if (s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    STATE = NUMBER_STATE;
                    i --;
                }else if (s.charAt(i) == ')'){
                    compute(data_stack,operation_stack);
                }
                break;
            }
        }
        if (number != 0){
            data_stack.push(number);
            compute(data_stack,operation_stack);
        }
        if (number == 0 && data_stack.isEmpty()){
            return 0;
        }
        return data_stack.peek();
    }

    /**
     * 计算栈内的值
     * @param data_stack
     * @param operation_stack
     */
    public void compute(Stack<Integer> data_stack,Stack<Character> operation_stack){
        if (data_stack.size() < 2){
            return;
        }

        //假设给定的字符串都是合法的表达式
        int num2 = data_stack.peek();
        data_stack.pop();
        int num1 = data_stack.peek();
        data_stack.pop();
        if (operation_stack.peek() == '+'){
            data_stack.push(num1 + num2);
        }
        if (operation_stack.peek() == '-'){
            data_stack.push(num1 - num2);
        }
        operation_stack.pop();
    }
}
