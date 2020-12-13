package com.sen.datastructuresandalgorithms.stack;

/**
 * @class: Calculator
 * @description: 栈-计算器实现--中缀表达式
 * @author: L_sen
 * @create: 2020-12-12 13:44
 **/
public class Calculator {

    public static void main(String[] args) {
        String experession = "30+2*6-2/2";
        Stack numStack = new Stack(10);
        Stack operStack = new Stack(10);
        int num1;
        int num2;
        int oper;
        int res;
        char ch;// 每次扫描得到的char存到ch
        boolean flag = false;// 标志位
        StringBuilder chStr = new StringBuilder();// 存放多位数
        for (int i = 0; i < experession.length(); i++) {
            ch = experession.charAt(i);
            // 判断ch是什么，做相应处理
            if (operStack.isOper(ch)) {
                if (flag) {
                    numStack.push(Integer.parseInt(chStr.toString()));
                    chStr = new StringBuilder();
                    flag = false;
                }
                if (!operStack.isEmpty()) {
                    // 如果符号栈不为空，则进行比较，如果当前的操作符优先级小于或者等于前一个栈中的操作符，
                    // 取出数栈pop两数并运行;
                    if (operStack.prority(ch) <= operStack.prority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 存储结果到数栈
                        numStack.push(res);
                        // 存储当前符号到符号栈
                        operStack.push(ch);
                    } else {
                        // 如果当前操作符优先级大于前一个栈中的操作符
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }
            } else { // 如果是数，则直接入数栈
                chStr.append(ch);
                flag = true;
                if (i == experession.length() - 1) {
                    numStack.push(Integer.parseInt(chStr.toString()));
                }
            }
        }
        while (true) {
            if (operStack.isEmpty()) {
                System.out.println(experession + " = " + numStack.pop());
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            // 存储结果到数栈
            numStack.push(res);
        }
    }
}

class Stack {
    private final int maxSize; // 栈的大小
    private final int[] stack; // 数组  数据存放
    private int top = -1; // 表示栈顶

    // 构造器
    public Stack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 返回当前栈顶的值
    public int peek() {
        return stack[top];
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

    // 返回运算符优先级，优先级是程序员来确定，优先级使用数字表示
    public int prority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        }
        if (oper == '+' || oper == '-') {
            return 0;
        }
        return -1;// 假定目前表达式只有加减乘除
    }

    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; // 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}