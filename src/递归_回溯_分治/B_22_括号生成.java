package 递归_回溯_分治;

import java.util.ArrayList;
import java.util.List;

public class B_22_括号生成 {
    /**
     * 递归放置左右括号，左括号的数量等于右括号的数量等于n，且左括号必须先于右括号放置
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n){
        List<String> result = new ArrayList<>();
        generate("",n,n,result);
        return result;
    }

    /**
     * @param s：有效的括号组合
     * @param left：当前还可以放置左括号的数量
     * @param right：当前还可以放置右括号的数量
     * @param result
     */
    private void generate(String s, int left, int right, List<String> result) {
        if (left == 0 && right == 0){
            result.add(s);
            return;
        }
        //可以放左括号时添加左括号
        if (left > 0){
            generate(s+"(",left - 1,right,result);
        }
        //当左括号数量比右括号数量多且可以放右括号时添加右括号
        if (right > 0 && left < right){
            generate(s+")",left,right - 1,result);
        }
    }
}
