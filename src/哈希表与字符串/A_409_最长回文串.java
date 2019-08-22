package 哈希表与字符串;

public class A_409_最长回文串 {

    /**
     * 统计各个字符出现的次数，遍历每个字符，如果这个字符出现的次数是2的整数倍，最大回文串的长度就加
     * 上该次数，否则就加上该次数减一且可以从这些出现次数为奇数的字符中拿出一个作为回文串的中心点。
     * @param s
     * @return
     */
    public int longestPalindrome(String s){
        //字符hash，统计相应字符的个数
        int[] char_map = new int[128];
        //回文串的最大长度
        int max_length = 0;
        //当有字符出现的次数为奇数时，表示可以添加一个中心点
        int flag = 0;
        //统计字符个数
        for (int i = 0;i < s.length();i ++){
            char_map[s.charAt(i)] ++;
        }
        for (int i = 0;i < char_map.length;i ++){
            if (char_map[i] % 2 == 0){
                max_length += char_map[i];
            }else {
                max_length += char_map[i] - 1;
                flag = 1;
            }
        }
        return max_length + flag;
    }
}
