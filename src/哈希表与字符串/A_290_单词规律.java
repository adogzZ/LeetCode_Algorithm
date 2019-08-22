package 哈希表与字符串;

import java.util.HashMap;
import java.util.Map;

public class A_290_单词规律 {

    public boolean wordPattern(String pattern,String str){
        //单词 -- pattern字符的映射
        Map<String,Character> word_map = new HashMap<>();
        //pattern字符是否已被映射
        boolean[] used = new boolean[128];
        //临时保存拆分出来的单词
        String word = "";
        //当前单词对应的pattern字符的位置
        int pos = 0;
        //在str尾部加入一个空格(在处理最后一个单词时不用做特殊判断)
        str += " ";
        for (int i = 0;i < str.length();i ++){
            //扫描过一个单词
            if (str.charAt(i) == ' '){
                //单词的个数多了
                if (pos == pattern.length()){
                    return false;
                }
                //如果此单词第一次出现
                if (!word_map.containsKey(word)){
                    //如果此单词映射的pattern字符已经被用过了
                    if (used[pattern.charAt(pos)]){
                        return false;
                    }
                    word_map.put(word,pattern.charAt(pos));
                    used[pattern.charAt(pos)] = true;
                }else {//此单词已经出现过了
                    //判断与此单词相映射的pattern字符是否与当前的pattern字符相同
                    if (word_map.get(word) != pattern.charAt(pos)){
                        return false;
                    }
                }
                word = "";
                pos ++;
            }else {
                word += str.charAt(i);
            }
        }
        //pattern字符多了
        if (pos != pattern.length()){
            return false;
        }
        return true;
    }
}
