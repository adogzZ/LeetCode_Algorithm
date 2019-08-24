package 搜索;

import java.util.*;

public class B_127_单词接龙 {

    /**
     * 单词与从beginWord到该单词步数的映射
     */
    class Pair {
        String vertex;
        int step;

        public Pair(String vertex, int step) {
            this.vertex = vertex;
            this.step = step;
        }
    }
    /**
     * 字典中单词之间的关系，用一张图来表示；如果两个单词只有一个字母不同，其他字母都相同，则这两个
     * 单词就相连，表示一条边。从beginWord出发，到endWord之间所有路径中顶点最少的那条路径上的顶点数量
     * 就是最短转换序列的长度。
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList){
        int result = 0;
        //用一个map来表示图的邻接表
        Map<String,List<String>> graph = new HashMap<>();
        //构造单词之间的关系图
        generate_graph(beginWord,wordList,graph);
        //广度优先搜索图中两顶点之间的最少顶点数
        result = BFS_graph(beginWord,endWord,graph);
        return result;
    }

    /**
     * 广度优先搜索，队列中存储的是顶点与步数的映射
     * @param beginWord
     * @param endWord
     * @param graph
     * @return
     */
    private int BFS_graph(String beginWord, String endWord, Map<String, List<String>> graph) {
        //广度优先搜索队列
        Queue<Pair> queue = new LinkedList<>();
        //用来标记已访问过的顶点（入队之前标记）
        Set<String> visit = new HashSet<>();
        Pair pair = new Pair(beginWord,1);
        queue.add(pair);
        visit.add(beginWord);
        while (!queue.isEmpty()){
            String word = queue.peek().vertex;
            int step = queue.peek().step;
            queue.remove();
            //如果搜索到endWord，就返回步数
            if (word.equals(endWord)){
                return step;
            }
            for (int i = 0;i < graph.get(word).size();i ++){
                //没有被访问，就入队访问
                if (!visit.contains(graph.get(word).get(i))){
                    pair = new Pair(graph.get(word).get(i),step + 1);
                    queue.add(pair);
                    visit.add(graph.get(word).get(i));
                }
            }
        }
        return 0;
    }

    /**
     * 单词之间的关系图的构建
     * @param beginWord
     * @param wordList
     * @param graph
     */
    private void generate_graph(String beginWord, List<String> wordList, Map<String, List<String>> graph) {
        //字典中有可能没有beginWord
        wordList.add(beginWord);
        //初始化图的邻接表
        for (int i = 0;i < wordList.size();i ++){
            List<String> edge = new ArrayList<>();
            graph.put(wordList.get(i),edge);
        }
        //边的连接
        for (int i = 0;i < wordList.size();i ++){
            for (int j =i + 1;j < wordList.size();j ++){
                if (is_connect(wordList.get(i),wordList.get(j))){
                    //无向边
                    graph.get(wordList.get(i)).add(wordList.get(j));
                    graph.get(wordList.get(j)).add(wordList.get(i));
                }
            }
        }
    }

    /**
     * 判断两个单词之间是否只差一个字母，即两单词之间是否有边相连
     * @param word1
     * @param word2
     * @return
     */
    private boolean is_connect(String word1, String word2) {
        //标记单词word1和word2不相等字符的个数
        int num = 0;
        for (int i = 0;i < word1.length();i ++){
            if (word1.charAt(i) != word2.charAt(i)){
                num ++;
            }
        }
        return num == 1;
    }
}
