package problem250907;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea2817_jwh {

    private static int N = 0;
    private static int K = 0;
    private static int cnt = 0;
    private static boolean[] isSelected;
    private static int[] numbers;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/250907/swea2817.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T ; t++){
            sb.append((char) 0x23).append(t).append((char) 0x20);
            st = new StringTokenizer(br.readLine());

            // constants variables init
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

            // init static variables
            cnt = 0;
            numbers = new int[N];
            isSelected = new boolean[N];

            for(int i = 0 ; i < N ; i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }
            subsetSummary(0);

            sb.append(cnt).append("\n"); // add result
        }

        System.out.print(sb.toString());
    }

    private static void subsetSummary(int idx) {
        if(idx == N) { // 재귀 함수의 종료 조건
            // 재귀 종료 조건에는 1 - N개의 조합들이 쏟아짐!

            int sum = 0;
            for(int i = 0 ; i < N ; i ++){
                if(isSelected[i]){
                    int num = numbers[i];
                    sum += num;
                }
            }

            if(sum == K){ // 찾는 K라면
                cnt++; // 개수 증가
            }
            return;
        }

        /**
         * 부분 조합에 대한 로직!
         * 재귀 함수를 이용했으므로, 각 숫자 요소에 대해 선택을 하거나 하지 않음 '만' 결정함.
         * 그후, 재귀의 종료 조건에 도달하면 각 요소에 대해 선택한 경우의 수들이 도착함.
         */

        isSelected[idx] = true;
        subsetSummary(idx+1);

        isSelected[idx] = false;
        subsetSummary(idx+1);
    }
}
