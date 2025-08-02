package problem250803;
// X보다 작은 수
// https://www.acmicpc.net/submit/10871

import java.util.Scanner;

public class boj10871_jwh {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();

        for(int i = 0 ; i < n; i ++){
            int num = sc.nextInt();
            if((num - x) < 0) {
                sb.append(num).append((char) 0x20);
            }
        }
        System.out.println(sb.toString());


    }
}
