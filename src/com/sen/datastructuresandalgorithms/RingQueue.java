package com.sen.datastructuresandalgorithms;

/**
 * @ClassName RingQueue
 * @Deacription 数组模拟环形队列
 * @Author SEN
 * @Date 2020/6/25 0025 12:20
 * @Version 1.0
 **/

public class RingQueue {
    public static void main(String[] args) {
        RingArrayQueue ringArrayQueue = new RingArrayQueue(5);
        ringArrayQueue.addQueue(1);
        ringArrayQueue.addQueue(2);
        ringArrayQueue.addQueue(3);
        ringArrayQueue.addQueue(4);
        int num = ringArrayQueue.getQueue();
        int headQueueData = ringArrayQueue.getHeadQueueData();
        System.out.println(num);
        System.out.println(headQueueData);
        ringArrayQueue.addQueue(10);
        ringArrayQueue.showQueue();
    }
}

class RingArrayQueue {
    // 队列的长度
    private int maxSize;
    // 队列的头 front变量的含义做一个调整: front 就指向队列的第一个 元素，也就是说arr[front]就是队列的第一个元素
    // front的初始值= 0
    private int front;
    // 队列的尾 rear变量的含义做- -个调整: rear指向队列的最后- -个元素的后-一个位置.因为希望空出- -个空间做为约定.
    //rear的初始值= 0
    private int rear;
    // 用于存放数据,数组模拟队列
    private int[] arr;

    // 创建队列构造器
    public RingArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 判断队列是否已满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 添加数据到队列 -- 进队列
    public void addQueue(int num) {
        if (isFull()) {
            throw new RuntimeException("队列已满，无法添加!");
        }
        arr[rear] = num;
        rear = (rear + 1) % maxSize;
    }

    // 获取队列数据 -- 出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法取出!");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 查看队列
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法查看");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.println((i % maxSize) + "========" + arr[i % maxSize]);
        }
    }

    // 查看队列头部数据
    public int getHeadQueueData() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法查看");
        }
        return arr[front];
    }

    // 求出有效数值
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}