package 二叉树与图;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class B_207_课程表 {

    /**
     * 图的邻接表节点
     */
    public class GraphNode {
        int label;
        List<GraphNode> neighbors = new ArrayList<>();

        public GraphNode(int label) {
            this.label = label;
        }
    }

    /**
     * 深度优先搜素图中的环
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses,int[][] prerequisites){
        //根据题目中的条件构造一个有向图
        GraphNode[] graph = new GraphNode[numCourses];
        //表示图中各个顶点的访问状态
        int[] visit = new int[numCourses];
        //初始化图，创建图的顶点，并标识未访问状态
        for (int i = 0;i < numCourses;i ++){
            graph[i] = new GraphNode(i);
            visit[i] = -1;
        }
        //创建图，连接图的顶点
        for (int i = 0;i < prerequisites.length;i ++){
            //课程0的学习依赖于课程1，所以课程1指向课程0
            graph[prerequisites[i][1]].neighbors.add(graph[prerequisites[i][0]]);
        }
        //如果顶点没有被访问过，就DFS搜索该顶点，如果DFS过程中遇到了环，就返回false
        for (int i = 0;i < graph.length;i ++){
            if (visit[i] == -1 && !DFS_graph(graph[i],visit)){
                return false;
            }
        }
        return true;
    }

    /**
     * DFS
     * @param graphNode
     * @param visit
     * @return
     */
    private boolean DFS_graph(GraphNode graphNode, int[] visit) {
        //表示正在访问
        visit[graphNode.label] = 0;
        //DFS搜索该节点的邻居节点
        for (int i = 0;i < graphNode.neighbors.size();i ++){
            //没有被访问过就DFS搜索该邻居节点
            if (visit[graphNode.neighbors.get(i).label] == -1){
                //搜索过程中有环
                if (!DFS_graph(graphNode.neighbors.get(i),visit)){
                    return false;
                }//该邻居节点正在被访问，说明有环
            }else if (visit[graphNode.neighbors.get(i).label] == 0){
                return false;
            }
        }
        //访问完成
        visit[graphNode.label] = 1;
        return true;
    }

    /**
     * 改进的广度优先搜索实现拓扑排序
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish_(int numCourses,int[][] prerequisites){
        //根据题目中的条件构造一个有向图
        GraphNode[] graph = new GraphNode[numCourses];
        //表示图中各个顶点的入度
        int[] degree = new int[numCourses];
        //初始化图，创建图的顶点，并标识顶点的入度初始时为0
        for (int i = 0;i < numCourses;i ++){
            graph[i] = new GraphNode(i);
            degree[i] = 0;
        }
        //创建图，连接图的顶点
        for (int i = 0;i < prerequisites.length;i ++){
            //课程0的学习依赖于课程1，所以课程1指向课程0
            graph[prerequisites[i][1]].neighbors.add(graph[prerequisites[i][0]]);
            //课程0的入度++
            degree[prerequisites[i][0]] ++;
        }
        Queue<GraphNode> queue = new LinkedList<>();
        //把入度为0的顶点放入队列中，先访问入度为0的顶点
        for (int i = 0;i < graph.length;i ++){
            if (degree[i] == 0){
                queue.add(graph[i]);
            }
        }
        while (!queue.isEmpty()){
            GraphNode node = queue.peek();
            queue.remove();
            for (int i = 0;i < node.neighbors.size();i ++){
                //邻居的入度--
                degree[node.neighbors.get(i).label] --;
                if (degree[node.neighbors.get(i).label] == 0){
                    queue.add(node.neighbors.get(i));
                }
            }
        }
        //遍历入度数组，判断是否有节点入度不为0
        for (int i = 0;i < degree.length;i ++){
            if (degree[i] > 0){
                return false;
            }
        }
        return true;
    }
}
