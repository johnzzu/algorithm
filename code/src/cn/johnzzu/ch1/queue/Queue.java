package cn.johnzzu.ch1.queue;

/**
 * @author john
 * @version 1.0
 * @description 队列，FIFO
 * @date 2020/7/26 10:47 上午
 */
public interface Queue<T> extends Iterable<T> {
    /**
     * 入队：将元素存入队尾
     *
     * @param item 要存放的元素
     */
    void enqueue(T item);

    /**
     * 出队：从队列中取出队首的元素
     *
     * @return 取出的队首元素
     */
    T dequeue();

    /**
     * 查看队列大小
     *
     * @return 队列大小
     */
    int size();

    /**
     * 查看队列是否为空
     *
     * @return 队列为空，返回true；不为空，返回false
     */
    boolean isEmpty();

}
