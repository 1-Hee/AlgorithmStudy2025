package problem250803;
// 과제 안 내신 분..?
// https://www.acmicpc.net/problem/5597

import java.util.Scanner;

public class boj5597_jwh {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int x = 0 ;

        for(int i = 0 ; i < 28; i++){
            int n = sc.nextInt();
            x |= (1 << (n-1));
        }

        for(int i = 0 ; i < 30 ; i ++){
            if(((x >> i) & 1) == 0){
                sb.append(i+1).append("\n");
            }
        }

        System.out.println(sb.toString());

    }
}
