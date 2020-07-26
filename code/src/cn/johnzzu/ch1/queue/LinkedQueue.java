package cn.johnzzu.ch1.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author john
 * @version 1.0
 * @description 链表实现队列
 * @date 2020/7/26 10:44 上午
 */
public class LinkedQueue<T> implements Queue<T> {
    /**
     * 使用链表存储元素，需要两个指针记录队首、队尾元素
     */
    private Node first;
    private Node last;

    /**
     * 链表实际存储的元素个数
     */
    private int n;

    public LinkedQueue() {
        first = last = null;
        n = 0;
    }

    /**
     * 定义链表类
     * 包含存储的元素本身、对下一个节点的引用
     *
     */
    private class Node {
        public T item;
        public Node next;
    }

    /**
     * 入队：将元素存入队尾
     *
     * @param item 要存放的元素
     */
    @Override
    public void enqueue(T item) {
        // 队尾节点变更
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    /**
     * 出队：从队列中取出队首的元素
     *
     * @return 取出的队首元素
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    /**
     * 查看队列大小
     *
     * @return 队列大小
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * 查看队列是否为空
     *
     * @return 队列为空，返回true；不为空，返回false
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> stack = new LinkedQueue<>();
        stack.enqueue(1);
        stack.enqueue(5);
        stack.enqueue(9);
        stack.dequeue();
        stack.dequeue();
        stack.enqueue(3);
        stack.enqueue(7);
        for (Integer num : stack) {
            System.out.println(num);
        }
    }
}
