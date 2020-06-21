package com.sen.DataStructuresAndAlgorithms;

import java.io.*;

/**
 * @ClassName SparseArray
 * @Deacription 稀疏数组--保存五子棋，地图；压缩二维数组
 * @Author SEN
 * @Date 2020/6/20 0020 13:13
 * @Version 1.0
 **/

public class SparseArray {

    public static void main(String[] args) {
        // 初始化一个二维数组表示棋盘
        // 定义 0为无棋 1为黑棋 2为白棋
        // 初始化为 [1,2]=1 --第二行第三列为黑棋，[2,3]=2 --第三行第四列为白棋
        int[][] chessArr = new int[5][5];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        // 二维数组转为稀疏数组
        int[][] sparseArray = toSparseArray(chessArr);
        String filePath = "e:/棋盘记录.data";
        // 保存为文件
        saveInFile(filePath,sparseArray);
        // 读取文件转为稀疏数组
        int[][] sparseArr = readFileToSparseArr(filePath);
        // 稀疏数组转二维数组
        int[][] newChessArr = toChessArr(sparseArr);
        // 打印还原的棋盘
        for (int[] row : newChessArr) {
            for (int data : row) {
                System.out.print(data+"\t");
            }
            System.out.println();
        }
    }

    /**
     * @param chessArr
     * @return sparseArr
     * @Deacription 二维数组转稀疏数组
     */
    public static int[][] toSparseArray(int[][] chessArr) {
        int sum = 0;
        // 遍历有效值
        for (int[] row : chessArr) {
            for (int data : row) {
                // 获取有效值总数
                if (data != 0) {
                    sum++;
                }
            }
        }
        // 定义稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;
        // 遍历二维数组，转换为稀疏数组
        // 定义一个变量，用来改变稀疏数组的记录行
        int count = 0;
        for (int row = 0; row < chessArr.length; row++) {
            for (int col = 0; col < chessArr[row].length; col++) {
                // 当遍历的数值为有效数值
                if (chessArr[row][col] != 0) {
                    count++;
                    // 记录数据所在行
                    sparseArr[count][0] = row;
                    // 记录数据所在列
                    sparseArr[count][1] = col;
                    // 记录数据值
                    sparseArr[count][2] = chessArr[row][col];
                }
            }
        }
        return sparseArr;
    }

    /**
     * @param sparseArr
     * @return ChessArr
     * @Deacription 稀疏数组转二维数组
     */
    public static int[][] toChessArr(int[][] sparseArr) {
        // 稀疏数组转二维数组
        int rowNum = sparseArr[0][0];
        int colNum = sparseArr[0][1];
        int[][] newArr = new int[rowNum][colNum];
        for (int row = 1; row < sparseArr.length; row++) {
            newArr[sparseArr[row][0]][sparseArr[row][1]] = sparseArr[row][2];
        }
        return newArr;
    }

    /**
    * @method saveInFile
    * @Author L_SEN
    * @Description 将稀疏数组保存到文件中
    * @Date 2020/6/21 0021
    * @Param [filePath, arr]
    * @return void
    **/
    public static void saveInFile(String filePath, int[][] arr) {
        // 创建文件对象
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            // 创建写文件对象
            fileWriter = new FileWriter(file);
            for (int[] row : arr) {
                for (int data : row) {
                    fileWriter.write(data + ",");
                }
                fileWriter.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("写入文件出错");
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                    System.out.println("写入文件成功");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
    * @method readFileToSparseArr
    * @Author L_SEN
    * @Description 读取文件转换为稀疏数组
    * @Date 2020/6/21 0021
    * @Param [filePath]
    * @return int[][]
    **/
    public static int[][] readFileToSparseArr(String filePath){
        File file =new File(filePath);
        BufferedReader br = null;
        // 定义行参数，用于记录数组行数
        int row = 0;
        String line;
        // 定义一个稀疏数组
        int[][] sparseArr = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                row++;
            }
            // 得到初始化稀疏数组
            sparseArr = new int[row][3];
            // 记录赋值行数
            int count = 0;
            // 再读一遍赋值稀疏数组
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(",");
                sparseArr[count][0] = Integer.valueOf(temp[0]);
                sparseArr[count][1] = Integer.valueOf(temp[1]);
                sparseArr[count][2] = Integer.valueOf(temp[2]);
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sparseArr;
    }
}
