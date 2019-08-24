package 搜索;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C_126_单词接龙II {

    /**
     * 广度优先搜索队列元素，记录每个节点的前驱，这样就记录下了搜索路径
     */
    class QItem{
        String vertex;
        int pre_pos;
        int step;

        public QItem(String vertex, int pre_pos, int step) {
            this.vertex = vertex;
            this.pre_pos = pre_pos;
            this.step = step;
        }
    }

    /**
     * 用一个由list实现的队列存储各个顶点，为队列节点增加前驱属性以记录各个路径
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord,String endWord,List<String> wordList){
        Map<String,List<String>> graph = new HashMap<>();
        //构图
        generate_graph(beginWord,wordList,graph);
        List<QItem> queue = new ArrayList<>();
        //记录不同路径下的endWord下标
        List<Integer> end_words = new ArrayList<>();
        BFS_graph(beginWord,endWord,graph,queue,end_words);
        List<List<String>> result = new ArrayList<>();
        //遍历所有最短转换序列
        for (int i = 0;i < end_words.size();i ++){
            List<String> path = new ArrayList<>();
            int pos = end_words.get(i);
            //从后向前遍历最短路径
            while (pos != -1){
                path.add(queue.get(pos).vertex);
                pos = queue.get(pos).pre_pos;
            }
            result.add(new ArrayList<>());
            for (int j = path.size() - 1;j >= 0;j --){
                result.get(i).add(path.get(j));
            }
        }
        return result;
    }

    /**
     * 广度优先遍历图，把顶点和路径信息记录到由list实现的队列中
     * @param beginWord
     * @param endWord
     * @param graph
     * @param queue
     * @param end_words
     */
    private void BFS_graph(String beginWord, String endWord, Map<String, List<String>> graph, List<QItem> queue, List<Integer> end_words) {
        //visit标记顶点的访问状态以及从beginWord到该顶点的最小步数
        Map<String,Integer> visit = new HashMap<>();
        QItem qitem = new QItem(beginWord,-1,1);
        //最短转换序列，为第一次搜索到endWord的步数
        int min_step = 0;
        queue.add(qitem);
        visit.put(beginWord,1);
        //队列头指针
        int front = 0;
        //用list实现的队列元素不空
        while (front != queue.size()){
            String word = queue.get(front).vertex;
            int step = queue.get(front).step;
            //已经找到了endWord，且所有最短转换序列都已经找完了
            if (min_step != 0 && min_step < step){
                break;
            }
            //如果搜索到endWord，记录此时的最小步数，此时的最小步数为最短转换序列
            if (word.equals(endWord)){
                min_step = step;
                //记录endWord在queue中的下标
                end_words.add(front);
            }
            //顶点word的邻居节点
            List<String> neighbors = graph.get(word);
            for (int i = 0;i < neighbors.size();i ++){
                //如果该邻居顶点没有被访问过或者多条最短路径经过该顶点
                if (!visit.containsKey(neighbors .get(i)) || visit.get(neighbors.get(i)) == step + 1){
                    qitem = new QItem(neighbors.get(i),front,step + 1);
                    queue.add(qitem);
                    visit.put(neighbors.get(i),step + 1);
                }
            }
            //出队
            front ++;
        }
    }

    /**
     * 单词之间的关系图的构建，要考虑字典中已经有beginWord的情况
     * @param beginWord
     * @param wordList
     * @param graph
     */
    private void generate_graph(String beginWord, List<String> wordList, Map<String, List<String>> graph) {
        int has_begin_word = 0;
        for (int i = 0;i < wordList.size();i ++){
            //字典中已经有beginWord了
            if (wordList.get(i).equals(beginWord)){
                has_begin_word = 1;
            }
            List<String> edge = new ArrayList<>();
            graph.put(wordList.get(i),edge);
        }
        //字典中没有beginWord的情况
        if (has_begin_word == 0){
            List<String> edge = new ArrayList<>();
            wordList.add(beginWord);
            graph.put(beginWord,edge);
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
