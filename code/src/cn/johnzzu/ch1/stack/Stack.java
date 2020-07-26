package cn.johnzzu.ch1.stack;

/**
 * @author john
 * @version 1.0
 * @description 栈，LIFO
 * @date 2020/7/26 11:04 上午
 */
public interface Stack<T> extends Iterable<T> {
    /**
     * 压栈
     *
     * @param item 需要压栈的元素
     */
    void push(T item);

    /**
     * 出栈：移除并返回栈顶的元素
     *
     * @return 最近添加到栈顶的元素
     */
    T pop();

    /**
     * 查看栈顶元素
     *
     * @return 最近添加到栈顶的元素
     */
    T peek();

    /**
     * 查看栈的大小
     *
     * @return 返回栈的大小，包含了多少个元素
     */
    int size();

    /**
     * 判断栈是否为空
     *
     * @return 栈为空，返回true；不为空，返回false
     */
    boolean isEmpty();
}
