package com.sen.datastructuresandalgorithms.recursion;

/**
 * @class: Queue8
 * @description: 八皇后问题
 * @author: L_sen
 * @create: 2020-12-20 16:29
 **/
public class Queue8 {

    int max = 8;
    int count = 0;
    int[] array = new int[max];

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println(queue8.count);
    }

    // 编写一个方法，放置第N个皇后
    // 每一次递归，都会for循环
    private void check(int n){
        if (n==max){ // n=8时，8个皇后已然放好了
            print();
            count++;
            return;
        }
        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前的皇后放到第一列
            array[n] = i;
            // 判断当放置第N个皇后到i列时是否冲突
            if (judge(n)){// 不冲突
                // 接着放N+1个皇后，开始递归
                check(n+1);
            }
            // 如果冲突则继续执行array[n] = i;即将第N个皇后，放置在本行的后移一个位置
        }
    }

    // 检测该皇后是否和已摆放的冲突
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            // array[i] == array[n] 表示该皇后是否与前面的n-1个皇后同一列
            // Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断该皇后是否与第i个皇后同一斜线
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }
    
    // 将摆放的位置输出
    private void print(){
        for (int j : array) {
            System.out.print(j + "  ");
        }
        System.out.println();
    }
}
