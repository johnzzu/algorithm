package cn.johnzzu.ch1.queue;

import cn.johnzzu.ch1.stack.ArrayStack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author john
 * @version 1.0
 * @description 数组实现队列
 * @date 2020/7/26 10:45 上午
 */
public class ArrayQueue<T> implements Queue<T> {
    /**
     * 使用数组存储元素
     */
    private T[] data;

    /**
     * 数组实际存放的元素个数
     */
    private int n;

    /**
     * 队列指针，分别记录队首、队尾位置
     */
    private int first;
    private int last;

    public ArrayQueue() {
        data = (T[]) new Object[1];
        n = 0;
        first = 0;
        last = 0;
    }

    /**
     * 入队：将元素存入队尾
     *
     * @param item 要存放的元素
     */
    @Override
    public void enqueue(T item) {
        // 判断是否扩容
        if (n == data.length) {
            resize(2 * data.length);
        }
        data[last++] = item;
        if (last == data.length) {
            last = 0;
        }
        n++;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        T[] temp = (T[]) new Object[capacity];
        int length = data.length;
        for (int i = 0; i < n; i++) {
            int index = (first + i) % length;
            temp[i] = data[index];
        }
        data = temp;
        first = 0;
        last = n;
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
        T item = data[first];
        data[first] = null;
        n--;
        first++;
        if (first == data.length) {
            first = 0;
        }
        if (n > 0 && n == data.length / 4) {
            resize(data.length / 2);
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
        return n == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return n > i;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int index = (first + i) % data.length;
            T item = data[index];
            i++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> stack = new ArrayQueue<>();
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
