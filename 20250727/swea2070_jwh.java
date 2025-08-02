package problem250727;
// 2070. 큰 놈, 작은 놈, 같은 놈
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5QQ6qqA40DFAUq&categoryId=AV5QQ6qqA40DFAUq&categoryType=CODE&problemTitle=%ED%81%B0&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1

import java.util.Scanner;

public class swea2070_jwh {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        String prefix = "";

        for(int r = 0 ; r < n ; r ++){
            int x = sc.nextInt();
            int y = sc.nextInt();

            prefix = String.format("#%01d ", (r+1));
            sb.append(prefix);

            if(x < y) {
                sb.append((char) 0x3C); // <
            }else if (x > y){
                sb.append((char) 0x3E); // =
            } else {
                sb.append((char) 0x3D); // >
            }
            sb.append((char) 0x0D).append((char) 0x0A);
        }

        System.out.print(sb.toString());


    }
}
