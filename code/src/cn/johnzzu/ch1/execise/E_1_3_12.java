package cn.johnzzu.ch1.execise;

import cn.johnzzu.ch1.stack.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author john
 * @version 1.0
 * @description 逆序迭代器
 * @date 2020/7/26 4:32 下午
 */
public class E_1_3_12 {
    static class ReverseLinkedStack<T> implements Stack<T> {
        private Node first;
        private int n;

        public ReverseLinkedStack() {
            first = null;
            n = 0;
        }

        private class Node {
            private T item;
            private Node next;
            private Node prev;
        }

        /**
         * 压栈
         *
         * @param item 需要压栈的元素
         */
        @Override
        public void push(T item) {
            // 需要改变链表头节点的指向
            Node node = new Node();
            node.item = item;
            if (isEmpty()) {
                first = node;
            } else {
                node.next = first;
                first.prev = node;
                first = node;
            }
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
            first.prev = null;
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
        }

        public Iterator<T> reverse() {
            return new ReverseLinkedIterator();
        }

        private class ReverseLinkedIterator implements Iterator<T> {

            private Node current = first;

            {
                while (current != null && current.next != null) {
                    current = current.next;
                }
            }

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T item = current.item;
                current = current.prev;
                return item;
            }
        }

        public static ReverseLinkedStack<String> copy(ReverseLinkedStack<String> source) {
            ReverseLinkedStack<String> copy = new ReverseLinkedStack<>();
            for (Iterator<String> it = source.reverse(); it.hasNext(); ) {
                String s = it.next();
                copy.push(s);
            }
            return copy;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (T t : this) {
                sb.append("\t").append(t);
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        ReverseLinkedStack<String> stack = new ReverseLinkedStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i + "");
        }
        System.out.println("original:" + stack);
        ReverseLinkedStack<String> copy = ReverseLinkedStack.copy(stack);
        System.out.println("copy:" + copy);

    }

}
