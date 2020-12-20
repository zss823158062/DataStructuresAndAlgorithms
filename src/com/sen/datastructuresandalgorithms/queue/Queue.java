package com.sen.datastructuresandalgorithms.queue;

/**
 * @ClassName Queue
 * @Deacription 队列
 * @Author SEN
 * @Date 2020/6/21 0021 15:32
 * @Version 1.0
 **/
public class Queue {

    public static void main(String[] args) {
        ArrayQueueDemo();
    }

    public static void ArrayQueueDemo(){
        ArrayQueue arrayQueue = new ArrayQueue(5);
        arrayQueue.addQueue(1);
        arrayQueue.addQueue(2);
        arrayQueue.addQueue(3);
        arrayQueue.addQueue(4);
        arrayQueue.addQueue(5);
        int num = arrayQueue.getQueue();
        int headQueueData = arrayQueue.getHeadQueueData();
        System.out.println(num);
        System.out.println(headQueueData);

    }

}

class ArrayQueue {
    // 队列的长度
    private int maxSize;
    // 队列的头
    private int front;
    // 队列的尾
    private int rear;
    // 用于存放数据,数组模拟队列
    private int[] arr;

    // 创建队列构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 判断队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 添加数据到队列 -- 进队列
    public void addQueue(int num) {
        if (isFull()) {
            throw new RuntimeException("队列已满，无法添加!");
        }
        rear++;
        arr[rear] = num;
    }

    // 获取队列数据 -- 出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法取出!");
        }
        front++;
        return arr[front];
    }

    // 查看队列
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法查看");
        }
        for (int data : arr) {
            System.out.println(data);
        }
    }

    // 查看队列头部数据
    public int getHeadQueueData() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法查看");
        }
        return arr[front + 1];
    }
}