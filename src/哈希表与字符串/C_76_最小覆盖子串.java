package 哈希表与字符串;

import java.util.ArrayList;
import java.util.List;

public class C_76_最小覆盖子串 {

    public String minWindow(String s,String t){
        //包含t中所有字符包括重复字符的最小子串
        String result = "";
        //统计字符串t中出现了哪几种字符，可以优化算法的处理时间
        List<Integer> t_char = new ArrayList<>();
        //窗口头指针
        int begin = 0;
        //统计窗口中各个字符出现的次数
        int[] map_s = new int[128];
        //统计字符串t中各个字符出现的次数
        int[] map_t = new int[128];
        //统计字符串t中的各个字符出现的次数
        for (int i = 0;i < t.length();i ++){
            map_t[t.charAt(i)] ++;
        }
        //统计字符串t中出现了哪几种字符
        for (int i = 0;i < 128;i ++){
            if (map_t[i] > 0){
                t_char.add(i);
            }
        }
        //窗口滑动
        for (int i = 0;i < s.length();i ++){
            map_s[s.charAt(i)] ++;
            //移动窗口头指针，使窗口尽可能小
            while (begin < i){
                //头指针指向的字符一定是在t中出现的字符
                if (map_t[s.charAt(begin)] == 0){
                    //字符移出窗口（这行代码加不加都行，因为该字符没有在t中出现，并不影响最后的结果）
                    map_s[s.charAt(begin)] --;
                    begin ++;
                }else if (map_s[s.charAt(begin)] > map_t[s.charAt(begin)]){
                    //头指针指向的字符在窗口中出现的次数超过了t中该字符出现的次数
                    map_s[s.charAt(begin)] --;
                    begin ++;
                }else {
                    //其他的情况，头指针不需要移动
                    break;
                }
            }
            if (is_window_ok(map_s,map_t,t_char)){
                int window_len = i - begin + 1;
                if (window_len < result.length() || result == ""){
                    result = s.substring(begin,i + 1);
                }
            }
        }
        return result;
    }

    /**
     * 窗口中是否包含了t的全部字符包括重复字符
     * @param map_s
     * @param map_t
     * @param t_char
     * @return
     */
    private boolean is_window_ok(int[] map_s, int[] map_t, List<Integer> t_char) {
        for (int i = 0;i < t_char.size();i ++){
            if (map_s[t_char.get(i)] < map_t[t_char.get(i)]){
                return false;
            }
        }
        return true;
    }
}
