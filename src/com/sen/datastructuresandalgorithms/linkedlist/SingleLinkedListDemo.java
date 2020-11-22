package com.sen.datastructuresandalgorithms.linkedlist;

import java.util.Stack;

/**
 * @class: SingleLinkedList
 * @description: 单链表的实现
 * @author: L_sen
 * @create: 2020-11-21 15:26
 **/
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        // 无序添加
        SingleLinkedList list = new SingleLinkedList();
        list.add(hero1);
        list.add(hero2);
        list.add(hero3);
        list.add(hero4);
        list.list();
        System.out.println("=====================");
        // 有序添加
        SingleLinkedList list2 = new SingleLinkedList();
        list2.addByOrder(hero1);
        list2.addByOrder(hero3);
        list2.addByOrder(hero4);
        list2.addByOrder(hero2);
        // 修改链表节点
        HeroNode heroNode = new HeroNode(2, "卢俊义", "麒麟");
        list2.update(heroNode);
        list2.list();
        System.out.println("=====================");
        // 删除节点
        list2.delete(1);
        list2.delete(2);
        list2.list();

        // 获取节点数
        System.out.println("有效节点长度：" + SingleLinkedList.getLength(list2.getHead()));
        // 获取倒数第一个
        System.out.println(SingleLinkedList.getIndexNode(list2.getHead(),3));
        // 单链表的反转
        list.list();
        SingleLinkedList.reverse(list.getHead());
        System.out.println("单链表反转=============");
        list.list();
        // 倒序打印链表
        System.out.println("===============");
        SingleLinkedList.reversePrint(list.getHead());
    }
}

class SingleLinkedList {
    // 先初始化一个头节点,头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    // 添加节点到单向链表
    // 当不考虑编号顺序时，
    // 1、找到当前链表的最后节点
    // 2、将最后的这个节点的next指向新节点
    public void add(HeroNode node) {
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        // 当退出的时候，temp就指向了链表的最后
        temp.next = node;
    }

    // 遍历链表 显示
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
        }
        HeroNode temp = head.next;
        while (temp != null) {
            // 输出节点信息
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    // 编号顺序添加
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
        boolean flag = false; // 添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) { // 说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > node.no) { // 位置找到，添加在temp的后面
                break;
            }
            if (temp.next.no == node.no) { // 说明编号已存在
                flag = true;
                break;
            }
            temp = temp.next;// 后移
        }
        // 判断flag的值
        if (flag) {
            System.out.println("准备插入的编号已存在 ===" + node.no);
            return;
        }
        node.next = temp.next;
        temp.next = node;
    }

    // 修改节点的信息
    public void update(HeroNode newHeroNode) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 根据no修改节点
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break; // 遍历完了链表
            }
            if (temp.no == newHeroNode.no) {
                flag = true; // 找到修改的
                break;
            }
            temp = temp.next;
        }
        // 根据flag判断是否找到
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
            return;
        }
        System.out.println("没有找到需要修改编号的节点===" + newHeroNode.no);
    }

    // 1、找到现需要删除节点的前一个节点temp
    // 2、temp.next = temp.next.next
    // 3、被删除的节点将不会有其他引用指向，会被垃圾回收机制回收
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false; // 是否找到待删除的节点的前一个节点

        while (true) {
            if (temp.next == null) { // 到了链表最后
                break;
            }
            if (temp.next.no == no) { // 找到删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next; // 指向下一个的下一个节点
            return;
        }
        System.out.println("要删除的节点不存在====" + no);
    }

    // 获取单链表的节点的个数（如果带头节点，需求不统计头节点）
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode temp = head.next;
        while (temp != null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    // 查找链表中倒数第K个节点
    // 1、编写一个方法，接收head节点和index；
    // 2、index表示倒数第几个节点
    // 3、先把链表遍历一遍、得到链表长度，得到之后从第一个开始遍历size-index就可以得到

    public static HeroNode getIndexNode(HeroNode head,int index){
        if (head.next == null){
            return null;
        }
        int size = getLength(head);
        if (index <= 0 || index >= size){
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0;i<size-index;i++){
            temp = temp.next;
        }
        return temp;
    }

    // 取出原来每个节点到新链表的第一个，最后原来的head指向新的链表的第一个
    public static void reverse(HeroNode head){
        if (head.next == null || head.next.next == null){
            return;
        }
        HeroNode temp = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","");
        while (temp != null){
            next = temp.next; // 暂存当前节点的下一个节点
            temp.next = reverseHead.next;// 将下一个节点指向新链表的最前端
            reverseHead.next = temp; // 新链表的头指向节点
            temp = next; // 后移
        }
        // 重指向
        head.next = reverseHead.next;
    }

    public static void reversePrint(HeroNode head){
        if (head.next == null){
            return;
        }

        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        // 打印栈节点
        stack.forEach(System.out::println);
        while (stack.size() > 0 ){
            System.out.println(stack.pop());
        }
    }
}