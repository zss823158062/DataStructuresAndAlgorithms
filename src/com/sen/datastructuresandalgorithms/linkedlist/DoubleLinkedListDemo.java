package com.sen.datastructuresandalgorithms.linkedlist;

/**
 * @class: DoubleLinkedList
 * @description: 双向链表演示
 * @author: L_sen
 * @create: 2020-11-28 11:18
 **/
public class DoubleLinkedListDemo {
    
}

// 创建一个双向链表类
class DoubleLinkedList{
    // 定义一个头节点
    private final HeroNode2 head = new HeroNode2(0, "", "");
    public HeroNode2 getHead() {
        return head;
    }

    // 遍历链表 显示
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            // 输出节点信息
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }
    public void add(HeroNode2 node) {
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        // 当退出的时候，temp就指向了链表的最后
        temp.next = node;
        node.pre = temp;
    }

    // 修改节点的信息
    public void update(HeroNode2 newHeroNode) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 根据no修改节点
        HeroNode2 temp = head.next;
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
    public void delete(int no) {
        HeroNode2 temp = head;
        boolean flag = false; // 是否找到待删除的节点的前一个节点

        while (true) {
            if (temp.next == null) { // 到了链表最后
                break;
            }
            if (temp.no == no) { // 找到删除节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            temp.next.pre = temp.pre;
            return;
        }
        System.out.println("要删除的节点不存在====" + no);
    }
}



class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; // 指向下一个节点
    public HeroNode2 pre; // 指向前一个节点

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}