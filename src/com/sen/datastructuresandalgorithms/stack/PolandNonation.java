package com.sen.datastructuresandalgorithms.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;

/**
 * @class: PolandNonation
 * @description: 逆波兰表达式实现计算器
 * @author: L_sen
 * @create: 2020-12-12 15:54
 **/
public class PolandNonation {

    public static void main(String[] args) {
        String suffixExpression = " 3 4 + 5 * 6 - ";
        System.out.println(calculate(getListString(suffixExpression)));
    }

    public static List<String> getListString(String suffixExpression){
        return Arrays.asList(suffixExpression.split(" "));
    }

    public static int calculate(List<String> ls){
        // 创建 栈
        Stack<String> stack = new Stack<>();
        int res = 0;
        for (String item : ls){
            if (item.matches("\\d+")){//匹配的是多位数
                stack.push(item);
            }else {
                if (!item.matches("[\\+\\-\\*\\/]")){
                    throw new RuntimeException("非法运算符");
                }
                // pop出两个数并运算 再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                if (item.equals("+")){
                    res = num1 + num2;
                    stack.push(String.valueOf(res));
                    continue;
                }
                if (item.equals("-")){
                    res = num1 - num2;
                    stack.push(String.valueOf(res));
                    continue;
                }
                if (item.equals("*")){
                    res = num1 * num2;
                    stack.push(String.valueOf(res));
                    continue;
                }
                if (item.equals("/")){
                    res = num1 / num2;
                    stack.push(String.valueOf(res));
                }

            }
        }
        return Integer.parseInt(stack.pop());
    }
}
