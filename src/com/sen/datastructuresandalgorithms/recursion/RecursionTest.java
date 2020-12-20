package com.sen.datastructuresandalgorithms.recursion;

/**
 * @class: RecursionTest
 * @description: 递归算法
 * @author: L_sen
 * @create: 2020-12-16 21:13
 **/
public class RecursionTest {

    public static void main(String[] args) {
//        test(4);
        int factorial = factorial(3);
        System.out.println(factorial);
    }
    // 打印问题
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        } else {
            System.out.println("n = " + n);

        }
    }
    // 阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return factorial(n - 1) * n;
    }
}
