package 哈希表与字符串;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class B_187_重复的DNA序列 {

    /**
     * 通过子串与子串数量的映射统计出现超过一次的DNA序列
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s){
        Map<String,Integer> word_map = new HashMap<>();
        List<String> result = new ArrayList<>();
        //遍历DNA序列，记录所有长度为10的子序列
        for (int i =0;i <= s.length() - 10;i ++){
            String order = s.substring(i,i + 10);
            //第一次出现
            if (!word_map.containsKey(order)){
                word_map.put(order,1);
            }else {
                word_map.put(order,word_map.get(order) + 1);
            }
        }
        //遍历map，把数量大于1的序列返回
        for (String str: word_map.keySet()
             ) {
            if (word_map.get(str) > 1){
                result.add(str);
            }
        }
        return result;
    }

    //长度为10的DNA序列的所有种可能
    int[] g_hash_map = new int[1 << 20];
    //DNA字符数组
    static char[] DNA_CHAR = {'A','C','G','T'};
    /**
     * 位运算
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences_(String s){
        List<String> result = new ArrayList<>();
        if (s.length() < 10){
            return result;
        }
        int[] char_map = new int[128];
        char_map['A'] = 0;
        char_map['C'] = 1;
        char_map['G'] = 2;
        char_map['T'] = 3;
        //四进制整数
        int key = 0;
        //将前10个字符转成二进制整数
        for (int i = 0;i < 10;i ++){
            //类比于1234序列转成整数sum = sum * 10 + num，这里相当于4进制
            key = (key << 2) + char_map[s.charAt(i)];
        }
        //key序列出现一次
        g_hash_map[key] = 1;
        for (int i = 10;i < s.length();i ++){
            //干掉最高位
            key = key & 0b111111111111111111;
            key = (key << 2) + char_map[s.charAt(i)];
            g_hash_map[key] ++;
        }
        //遍历所有出现次数大于1的序列
        for (int i = 0;i < g_hash_map.length;i ++){
            if (g_hash_map[i] > 1){
                result.add(change_int_to_DNA(i));
            }
        }
        return result;
    }

    /**
     * 把整数序列转成DNA序列
     * @param DNA
     * @return
     */
    private String change_int_to_DNA(int DNA) {
        StringBuilder str = new StringBuilder();
        for (int i = 0;i < 10;i ++){
            str.insert(0,DNA_CHAR[DNA & 3]);
            DNA = DNA >> 2;
        }
        return str.toString();
    }
}
