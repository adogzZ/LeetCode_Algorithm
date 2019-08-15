import java.util.Arrays;

public class A_455_分发饼干 {
    /**
     * 贪心
     * 对孩子和饼干排序，小饼干先满足胃口小的孩子
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g,int[] s){
        //对孩子和饼干排序
        Arrays.sort(g);
        Arrays.sort(s);
        //第几块饼干
        int j = 0;
        //第几个孩子，即被满足的孩子数量
        int i = 0;
        //当所有的孩子被满足或者所有饼干被分配完时停止循环
        while (i < g.length && j < s.length){
            if (s[j] >= g[i]){
                i ++;
            }
            j ++;
        }
        //返回被满足的孩子数量
        return i;
    }
}
