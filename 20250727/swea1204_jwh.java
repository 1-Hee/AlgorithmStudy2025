package problem250727;
// 1204. [S/W 문제해결 기본] 1일차 - 최빈수 구하기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV13zo1KAAACFAYh&categoryId=AV13zo1KAAACFAYh&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1

import java.util.Arrays;
import java.util.Scanner;

public class swea1204_jwh {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        int[] points = new int[101]; // 점수 관리용, 0 ~ 100

        String prefix = "";
        for(int i = 0 ; i < n ; i ++){
            Arrays.fill(points, 0); // buffer clear
            int num = sc.nextInt();
            prefix = String.format("#%d ", num);
            sb.append(prefix);
            for(int j = 0 ; j < 1000; j ++){
                int pointX = sc.nextInt();
                points[pointX]++;
            }
            int max = 0;
            int idx = 0;

            for(int k = 0, len = points.length ; k < len ; k++){
                if(max <= points[k]){
                    idx = k;
                }
                max = Math.max(points[k], max);
            }
            sb.append(idx).append("\n");
        }

        System.out.println(sb.toString());


    }
}
