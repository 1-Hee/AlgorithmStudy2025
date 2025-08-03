package problem250727;
// 2072. 홀수만 더하기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=1&contestProbId=AV5QSEhaA5sDFAUq&categoryId=AV5QSEhaA5sDFAUq&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1&&&&&&&&&&

import java.util.Scanner;


public class swea2072_jwh {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n ; i ++){
            int sum = 0;
            for(int j = 0 ; j < 10 ; j ++){
                int x = sc.nextInt();
                int result = x & 1;                
                if(result > 0){
                    sum += x;
                }
            }
            sb.append("#").append((i+1)).append(" ").append(sum).append("\n");
        }

        System.out.print(sb.toString());
    }

}
