package problem250803;
// 바구니 뒤집기
// https://www.acmicpc.net/problem/10811

import java.util.Arrays;
import java.util.Scanner;

public class boj10811_jwh {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] list = new int[n];
        for(int i = 0 ; i < n ; i ++){
            list[i] = i+1;
        }

        for(int i = 0 ; i < m; i ++){
            int s = sc.nextInt() - 1;
            int e = sc.nextInt() - 1;ik
            if(s == e) continue;

            int[] section = Arrays.copyOfRange(list, s, e+1);

            for(int j = 0, len = section.length ; j < len; j ++){
                list[e-j] = section[j];
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n; i ++){
            sb.append(list[i]).append((char) 0x20);
        }
        System.out.println(sb.toString());

    }


}
