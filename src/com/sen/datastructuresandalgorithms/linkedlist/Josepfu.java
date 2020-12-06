package com.sen.datastructuresandalgorithms.linkedlist;


/**
 * @class: Josepfu
 * @description: 约瑟夫环形链表
 * @author: L_sen
 * @create: 2020-12-06 13:51
 **/
public class Josepfu {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
//        circleSingleLinkedList.showBoy();
        // 出圈测试
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

// 创建一个环形的单向链表
class CircleSingleLinkedList{
    private Boy first = new Boy(-1);
    // 添加小孩节点
    public void addBoy(int nums){
        if (nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;
        // 使用for循环创建
        for (int i = 1; i <= nums; i++) {
            // 根据编号创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1){
                first = boy;
                first.setNext(first);// 构成环
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // 遍历当前环形链表
    public void showBoy(){
        if (first == null){
            System.out.println("链表为空");
            return;
        }
        // 因为first不能动，需要一个辅助中间量
        Boy curBoy = first;
        while (true){
            System.out.println("小孩字的编号："+ curBoy.getNo());
            if (curBoy.getNext() == first){
                break; // 说明已遍历完毕
            }
            curBoy = curBoy.getNext(); // 后移
        }
    }

    /**
     * 出圈顺序
     * @param startNo 从第几个小孩开始数
     * @param countNum 表示数多少下
     * @param nums 表示有几个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums){
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        // 创建辅助指针
        Boy helper = first;
        // 事先应指向环形链表最后的节点
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 出圈操作 直到圈中只有一个节点
        while (helper != first) {
            // 让first和helper同时移动countNum - 1次
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这时first指向的节点就是出圈的节点
            System.out.println("出圈节点：" + first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后留在圈中的小孩编号：" + first.getNo());
    }

}


class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}