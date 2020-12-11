package com.sen.datastructuresandalgorithms.stack;

/**
 * @class: ArrayStackDemo
 * @description: 数组模拟栈
 * @author: L_sen
 * @create: 2020-12-06 16:56
 **/
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        System.out.println("入栈=====");
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(7);
        stack.push(9);
        stack.list();
        System.out.println("出栈=====");
        stack.pop();
        stack.pop();
        stack.list();
    }
}

class ArrayStack {
    private final int maxSize; // 栈的大小
    private final int[] stack; // 数组  数据存放
    private int top = -1; // 表示栈顶

    // 构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈-push
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            // 抛出异常处理
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 显示栈内容
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "]===" + stack[i]);
        }
    }
}