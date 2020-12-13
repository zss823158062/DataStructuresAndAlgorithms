package com.sen.datastructuresandalgorithms.stack;

import java.util.Stack;

import static java.lang.String.join;

/**
 * @class: InfixToSuffixNotation
 * @description: 中缀表达式转后缀表达式
 * @author: L_sen
 * @create: 2020-12-13 13:30
 **/
public class InfixToSuffixNotation {

    public static void main(String[] args) {
        String operation = "12+((2+3)*/4)-5";
        String s = InfixToSuffixNotationWithString(operation.trim().replace(" ", ""));
        try {
            int calculate = PolandNonation.calculate(PolandNonation.getListString(s));
            System.out.println(operation + "的后缀表达式：" + s);
            System.out.println(operation + " = " + calculate);
        } catch (Exception e) {
            System.out.println(operation + " 是非法的运算表达式");
        }
    }

    public static String InfixToSuffixNotationWithString(String infixNotation) {
        // 符号栈
        Stack<String> operationStack = new Stack<>();
        // 数栈
        Stack<String> numberStack = new Stack<>();
        // 用于拼接
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < infixNotation.length(); i++) {
            String ch = infixNotation.charAt(i) + "";
            // 如果扫描到最后一位，则不进行拼接。直接入栈
            if (i == infixNotation.length() - 1) {
                if (!ch.matches("\\d+")) {
                    throw new RuntimeException(infixNotation + " 是非法的运算公式");
                }
                numberStack.push(ch);
                numberStack.push(operationStack.pop());
            }
            // 如果是数，则拼接
            if (ch.matches("\\d+")) {
                temp.append(ch);
                continue;
            }
            if (!ch.matches("[+\\-*/]") && !ch.matches("[()]")) {
                throw new RuntimeException("非法操作符");
            } else {
                if (temp.length() > 0) {
                    numberStack.push(temp.toString());
                    temp.setLength(0);
                }
            }
            // 为运算符或者括号时
            // 符号栈为空或者为左括号
            if (operationStack.isEmpty() || "(".equals(ch)) {
                operationStack.push(ch);
                continue;
            }
            // 为运算符时
            if (ch.matches("[+\\-*/]")) {
                // 优先级高于栈顶运算符
                if (priority(ch.charAt(0)) <= priority(operationStack.peek().charAt(0))) {
                    // 优先级低于或者等于栈顶运算符
                    // 弹出运算符栈顶符号
                    String popStr = operationStack.pop();
                    // 压入数栈
                    numberStack.push(popStr);
                    // 压入当前运算符到符号栈
                }
                operationStack.push(ch);
                continue;
            }
            // 为右括号
            if (")".equals(ch)) {
                // 弹出符号栈压入数栈，直至遇到左括号
                while (!operationStack.peek().equals("(")) {
                    String popStr = operationStack.pop();
                    numberStack.push(popStr);
                }
                operationStack.pop();
            }
        }
        String[] strings = numberStack.toArray(new String[0]);
        return join(" ", strings);
    }

    public static int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        }
        if (oper == '+' || oper == '-') {
            return 1;
        }
        return -1;
    }

}
