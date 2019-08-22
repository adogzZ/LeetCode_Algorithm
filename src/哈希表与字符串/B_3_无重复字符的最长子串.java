package 哈希表与字符串;

public class B_3_无重复字符的最长子串 {

    /**
     * 窗口滑动 、 双指针扫描字符串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s){
        //窗口头指针
        int begin = 0;
        //最长无重复字符子串的长度
        int result = 0;
        //统计某字符是否重复
        boolean[] char_map = new boolean[128];
        //窗口开始滑动
        for (int i = 0;i < s.length();i ++){
            //子串中没有出现过这个字符
            if (!char_map[s.charAt(i)]){
                char_map[s.charAt(i)] = true;
            }else {
                //移动begin，到第一次出现该重复字符的下一位
                while (begin < i && s.charAt(begin) != s.charAt(i)){
                    //字符移出窗口
                    char_map[s.charAt(begin)] = false;
                    begin ++;
                }
                begin ++;
            }
            //窗口大小
            int window = i - begin + 1;
            result = result < window ? window : result;
        }
        return result;
    }
}
