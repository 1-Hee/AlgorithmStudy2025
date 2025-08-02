package problem250803;
// 음계
// https://www.acmicpc.net/problem/2920

import java.util.Scanner;

public class boj2920_jwh {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        int [] buffer = new int[8];

        for(int i = 0 ; i < 8 ; i ++){
            buffer[i] = sc.nextInt();
        }


        String res = "ascending";
        // ascending
        for(int i = 0 ; i < 7 ; i ++){
            int gap = buffer[i+1] - buffer[i];
            if(gap != 1) {
                res = "";
                break;
            }
        }

        if(!res.isEmpty()){
            System.out.println(res);
            return;
        }

        res = "descending";
        // descending
        for(int i = 0 ; i < 7 ; i ++){
            int gap = buffer[i] - buffer[i+1];
            if(gap != 1) {
                res = "mixed";
                break;
            }
        }

        System.out.println(res);


    }

}
