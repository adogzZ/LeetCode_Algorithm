package 栈_队列_堆;

import java.util.Comparator;
import java.util.PriorityQueue;

public class C_295_数据流中的中位数 {

    //最大堆和最小堆
    private PriorityQueue<Integer> big_heap;
    private PriorityQueue<Integer> small_heap;
    /**
     * 动态维护一个最大堆和一个最小堆，要求最大堆的堆顶比最小堆的堆顶元素小，这样就保证了中位数要么是两个
     * 堆顶元素的平均值，要么是其中一个堆顶元素。
     * 插入元素时要考虑三种情况：1、最大堆和最小堆元素数量相同；2、最大堆比最小堆多一个元素；3、最大堆比
     * 最小堆少一个元素
     * 获取中位数时：1、最大堆和最小堆元素个数相同；2、最大堆比最小堆多一个元素；3、最大堆比最小堆少一个元素
     */
    public C_295_数据流中的中位数() {
        //构造大顶堆
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };
        //java中默认堆是小顶堆，构造最大堆
        big_heap = new PriorityQueue<>(comparator);
        small_heap = new PriorityQueue<>();
    }

    //插入元素
    public void addNum(int num){
        if (big_heap.isEmpty()){
            big_heap.add(num);
            return;
        }
        //第一种情况
        if (big_heap.size() == small_heap.size()){
            if (num < big_heap.peek()){
                big_heap.add(num);
            }else {
                small_heap.add(num);
            }
        } else if (big_heap.size() > small_heap.size()){
            //第二种情况
            if (num > big_heap.peek()){
                //直接插入最小堆
                small_heap.add(num);
            }else {
                //平衡最大最小堆中元素的数量
                small_heap.add(big_heap.peek());
                big_heap.remove();
                big_heap.add(num);
            }
        }else if (big_heap.size() < small_heap.size()){
            //第三种情况
            if (num < small_heap.peek()){
                //直接插入最大堆
                big_heap.add(num);
            }else {
                big_heap.add(small_heap.peek());
                small_heap.remove();
                small_heap.add(num);
            }
        }
    }

    //获取中位数
    public double findMedian(){
        if (big_heap.size() == small_heap.size()){
            //两个堆大小相等的情况
            return (big_heap.peek() + small_heap.peek()) / 2.0;
        }else if (big_heap.size() > small_heap.size()){
            return big_heap.peek();
        }else {
            return small_heap.peek();
        }
    }
}
