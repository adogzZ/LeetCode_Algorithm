import java.util.Stack;

public class B_402_移掉K位数字 {
    /**
     * 用栈来存储数字字符串以及模拟删除操作，应贪心的选择高位数字较大的元素进行
     * 删除，得到的数字最小。
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num,int k){
        Stack<Integer> stack = new Stack<>();
        StringBuilder digits = new StringBuilder();
        //遍历字符串
        for (int i = 0;i < num.length();i ++){
            int digit = num.charAt(i) - '0';
            //当数字字符串长度不为0，高位数字较大，且还有k个元素可以删除
            while (!stack.isEmpty() && stack.peek() > digit && k > 0){
                //删除高位数字
                stack.pop();
                k --;
            }
            //如果此时数字为0且栈为空，就不需要添加；否则把数字压入栈中
            if (digit != 0 || !stack.isEmpty()){
                stack.push(digit);
            }
        }

        //如果还可以删除
        while (!stack.isEmpty() && k > 0){
            stack.pop();
            k --;
        }
        //把栈中的数字转换为字符串
        while (!stack.isEmpty()){
            digits.insert(0,stack.pop());
        }
        if (digits.toString().equals("")){
            return "0";
        }
        return digits.toString();
    }
}
