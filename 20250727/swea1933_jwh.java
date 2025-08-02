package problem250727;
// 1933. 간단한 N 의 약수
// https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=1&contestProbId=AV5PhcWaAKIDFAUq&categoryId=AV5PhcWaAKIDFAUq&categoryType=CODE&problemTitle=&orderBy=INQUERY_COUNT&selectCodeLang=ALL&select-1=1&pageSize=10&pageIndex=2

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class swea1933_jwh {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        int max = (int) Math.sqrt(n);

        ArrayList<Integer> dividers = new ArrayList<>();

        for(int i = 1 ; i <= max; i ++){
            if(n % i == 0){
                dividers.add(i);
                if((n / i) != i){
                    dividers.add((n / i));
                }
            }
        }
        Collections.sort(dividers);

        for(int i = 0, size = dividers.size(); i < size ; i++){
            int x = dividers.get(i);
            sb.append(x).append((char) 0x20);
        }

        System.out.println(sb.toString());

    }
}
