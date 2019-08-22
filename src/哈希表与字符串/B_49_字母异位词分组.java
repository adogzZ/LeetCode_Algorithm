package 哈希表与字符串;

import java.util.*;

public class B_49_字母异位词分组 {

    public List<List<String>> groupAnagrams(String[] strs){
        Map<String,List<String>> anagram = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        //遍历每个单词
        for (int i = 0;i < strs.length;i ++){
            //对单词排序
            char[] str_ = strs[i].toCharArray();
            Arrays.sort(str_);
            String str = new String(str_);
            //以排序后的单词为key
            if (!anagram.containsKey(str)){
                List<String> item = new ArrayList<>();
                anagram.put(str,item);
            }
            anagram.get(str).add(strs[i]);
        }
        for (List<String> strings: anagram.values()
             ) {
            result.add(strings);
        }
        return result;
    }

}
