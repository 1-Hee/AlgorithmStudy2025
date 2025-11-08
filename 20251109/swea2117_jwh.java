package problem251109;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class swea2117_jwh {

    static class HomeProtector {

        private int N, M;
        private int[][] homeMap;

        public HomeProtector(int n, int m, int[][] map) {
            this.N = n;
            this.M = m;
            this.homeMap = map;
        }

        private int countHome(int r, int c, int k) {
            int homeCount = 0;

            // 범위 최적화를 위한 값
            int weight = (k+1); // 임의의 좌표 r, c를 기준으로 가감할 가중치 weight
            int rowMin = Math.max(r - weight, 0); // 행의 최소 범위
            int rowMax = Math.min(r + weight, N); // 행위 최대 범위
            int colMin = Math.max(c - weight, 0); // 열의 최소 범위
            int colMax = Math.min(c + weight, N); // 열의 최대 범위

            for(int i = rowMin; i < rowMax ; i++){
                for(int j = colMin; j < colMax; j ++){
                    int dist = Math.abs(r - i) + Math.abs(c - j);
                    if(dist <= k-1){
                        if(homeMap[i][j] > 0) homeCount++;
                    }
                }
            }
            return homeCount;
        }

        public int findMaxHome() {
            int maxHome = 0;

            // K는 1부터 시작하여, 모든 집을 포함할 수 있는 최대 K까지 반복.
            // N이 최대 20이므로, K의 최대값은 40 내외, N=7이므로 K는 13 정도까지 확인하면 되지만
            // 여기서는 N+1 정도로 충분할 수 있을 것 같아서 범위 설정
            for(int k = 1; k <= N + 1 ; k++){
                int operationCost = k * k + (k - 1) * (k - 1); // 현재 K에서 기본 운영 비용 (고정지출)
                int cHomeMax = 0; // 현재 단계에서 찾은 집의 갯수

                for(int r = 0; r < N ; r++){
                    for(int c = 0; c < N; c++){
                        int count = countHome(r, c, k);
                        int profit = (count * M) - operationCost; // 순이익 계산
                        if(profit >= 0){ // 적자만 안나는 수준까지 허용해야 함.
                            cHomeMax = Math.max(cHomeMax, count);
                        }
                    }
                }
                maxHome = Math.max(cHomeMax, maxHome); // 전체 케이스에 대한 최대값 비교
            }
            return maxHome;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/20251109/swea2117.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] map = new int[N][N];

            for(int i = 0; i < N ; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    map[i][j] = x;
                }
            }

            HomeProtector protector = new HomeProtector(N, M, map);
            int result = protector.findMaxHome();
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());
    }
}
