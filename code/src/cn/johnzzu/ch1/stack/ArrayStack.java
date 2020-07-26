package cn.johnzzu.ch1.stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author john
 * @version 1.0
 * @description 数组实现栈
 * @date 2020/7/26 10:46 上午
 */
public class ArrayStack<T> implements Stack<T> {
    /**
     * 使用数组存储元素
     */
    private T[] data;

    /**
     * 数组实际存放的元素个数
     */
    private int n;

    public ArrayStack() {
        data = (T[]) new Object[1];
        n = 0;
    }

    /**
     * 压栈
     *
     * @param item 需要压栈的元素
     */
    @Override
    public void push(T item) {
        // 是否需要扩容
        if (n == data.length) {
            resize(2 * data.length);
        }
        data[n++] = item;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        data = Arrays.copyOf(data, capacity);
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
        T item = data[n - 1];
        data[n - 1] = null;
        n--;
        // 是否需要自动调整大小
        if (n > 0 && n == data.length / 4) {
            resize(data.length / 2);
        }
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
        return data[n - 1];
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
        return n == 0;
    }

    /**
     * 返回元素的迭代器，需要自己实现内部逻辑
     *
     * @return 迭代器
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int current = n;

        /**
         * 是否包含下一个元素
         *
         * @return 包含返回true；不包含返回false
         */
        @Override
        public boolean hasNext() {
            return current != 0;
        }

        /**
         * 返回下一个元素
         *
         * @return 下一个元素
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[--current];
        }

        /**
         * 迭代时，移除元素操作
         * 暂不支持该操作
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(5);
        stack.push(9);
        for (Integer num : stack) {
            System.out.println(num);
        }
        System.out.println(stack.pop());
    }
}
