package cn.johnzzu.ch1.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author john
 * @version 1.0
 * @description 链表实现栈
 * @date 2020/7/26 10:46 上午
 */
public class LinkedStack<T> implements Stack<T> {
    /**
     * 使用链表存储元素
     */
    private Node first;

    /**
     * 链表实际存储的元素个数
     */
    private int n;

    public LinkedStack() {
        first = null;
        n = 0;
    }

    /**
     * 定义链表类
     * 包含存储的元素本身、对下一个节点的引用
     */
    private class Node {
        public T item;
        public Node next;
    }

    /**
     * 压栈
     *
     * @param item 需要压栈的元素
     */
    @Override
    public void push(T item) {
        // 需要改变链表头节点的指向
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    /**
     * 出栈：移除并返回栈顶的元素
     *
     * @return 最近添加到栈顶的元素
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T item = first.item;
        first = first.next;
        n--;
        return item;
    }

    /**
     * 查看栈顶元素
     *
     * @return 最近添加到栈顶的元素
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.item;
    }

    /**
     * 查看栈的大小
     *
     * @return 返回栈的大小，包含了多少个元素
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * 判断栈是否为空
     *
     * @return 栈为空，返回true；不为空，返回false
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
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
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(5);
        stack.push(9);
        for (Integer num : stack) {
            System.out.println(num);
        }
        System.out.println(stack.pop());
    }
}
