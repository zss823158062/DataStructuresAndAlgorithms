package com.sen.datastructuresandalgorithms.recursion;


/**
 * @class: MiGong
 * @description: 迷宫-递归算法
 * @author: L_sen
 * @create: 2020-12-19 11:33
 **/
public class MiGong {

    public static void main(String[] args) {
        int[][] map = new int[8][7];
        // 上下全部置1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右全部置1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        // 使用递归回溯给小球找路
        System.out.println("结果===============");
        setWay(map, 1, 1);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }

    }
    // 试用递归回溯来给小球找路
    // 说明
    // 1、map代表地图
    // 2、i,j表示从地图的哪个位置出发（1,1）
    // 3、如果小球能到map[6][5]，则说明通路找到
    // 4、约定：当map[i][j]为0表示该点没有走过 当为1时表示墙，2表示通路可以走，3表示该点已经走过，但是走不通
    // 5、在走迷宫时，需要确定一个策略 下->右->上->左

    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 通路已找到
            return true;
        }
        if (map[i][j] == 0) { // 如果当前这个点还没有走过
            map[i][j] = 2;
            if (setWay(map, i + 1, j)) { //往下走
                return true;
            } else if (setWay(map, i, j + 1)) { // 向右走
                return true;
            } else if (setWay(map, i - 1, j + 1)) { // 向上走
                return true;
            } else if (setWay(map, i, j - 1)) { // 向左走
                return true;
            } else {
                // 该点走不通，是死路，置3
                map[i][j] = 3;
                return false;
            }
        } else {
            return false;
        }
    }
}
