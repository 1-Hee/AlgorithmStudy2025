package problem250727;
// 1545. 거꾸로 출력해 보아요
// https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=1&contestProbId=AV2gbY0qAAQBBAS0&categoryId=AV2gbY0qAAQBBAS0&categoryType=CODE&problemTitle=&orderBy=INQUERY_COUNT&selectCodeLang=ALL&select-1=1&pageSize=10&pageIndex=1

import java.util.Scanner;

public class swea1545_jwh {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();

        while(n >= 0){
            sb.append(n).append((char) 0x20);
            n--;
        }
        System.out.println(sb.toString());

    }
}
