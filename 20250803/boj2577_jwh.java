package problem250803;
// BOJ 2577. 숫자의 개수
// URL : https://www.acmicpc.net/problem/2577

import java.util.Arrays;
import java.util.Scanner;

public class boj2577_jwh {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int[] numBuffer = new int[10];
        int x = a * b * c;
        String strX = Integer.toString(x);
        Arrays.fill(numBuffer, 0);

        for(int i = 0, len = strX.length() ; i < len ; i++){
            int idx = ( strX.charAt(i) - '0' );
            numBuffer[idx]++;
        }

        for(int i = 0 ; i < 10 ; i ++){
            sb.append(numBuffer[i]).append((char)0x0A);
        }

        System.out.println(sb.toString());

    }
}
