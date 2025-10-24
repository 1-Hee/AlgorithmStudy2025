package problem251026;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class swea5656_jwh {

    // 중복 순열을 만들어주는 클래스
    static class CaseMaker {
        private int[] numbers;
        private int[] holders;
        private int K;
        private int N;
        private final ArrayList<int[]> caseList = new ArrayList<>();

        public CaseMaker(int n, int k){
            N = n;
            K = k;
            numbers = new int[N];
            holders = new int[K];
            for(int i = 0; i < N ; i ++){
                numbers[i] = i;
            }
        }

        public void getCases() {
            perm(0);
        }

        public ArrayList<int[]> getCaseList() {
            return this.caseList;
        }

        // 중복 순열 경우의 수 생성 재귀 함수
        private void perm(int cnt) {
            if(cnt == K){
                // 메모리 딥 카피 문제가 있어서 깊은 복사 진행...
                int[] mCase = new int[holders.length];
                System.arraycopy(holders, 0,  mCase, 0, mCase.length);
                this.caseList.add(mCase);
                return;
            }
            for(int i = 0 ; i < N ; i++){
                holders[cnt] = numbers[i];
                perm(cnt+1);
            }
        }
    }

    // 벽돌 깨기 시뮬레이션 클래스
    // 주어진 케이스 1개당 1회의 벽돌 파괴 작업을 수행하도록 설계
    static class BrickDestroyer {
        private int[][] mMap;
        private int[] mInstruction;
        private int R;
        private int C;

        public BrickDestroyer(int[] instruction, int[][] map) {
            if(map.length < 1) return;
            R = map.length;
            C = map[0].length;
            mMap = new int[R][C];
            mInstruction = new int[instruction.length];
            for(int r = 0; r < R; r++){
                System.arraycopy(map[r], 0, mMap[r], 0, C);
            }
            System.arraycopy(instruction, 0, mInstruction, 0, mInstruction.length);
        }

        private static int[] dx = {-1, 1, 0, 0};
        private static int[] dy = {0, 0, -1, 1};

        // 벽돌 파괴 함수
        private void destroyBrick(int x, int y) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[] {x , y});
            boolean[][] isVisited = new boolean[R][C];
            isVisited[x][y] = true;

            while(!queue.isEmpty()) {
                int[] pos = queue.poll();
                int cx = pos[0];
                int cy = pos[1];
                int value = mMap[cx][cy];
                if (value <= 1) continue;

                for(int d = 0; d < 4; d++){
                    for(int i = 1; i < value; i++){
                        int nx = cx + (dx[d] * i);
                        int ny = cy + (dy[d] * i);
                        if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                        if(isVisited[nx][ny]) continue;
                        if(mMap[nx][ny] == 0) continue;
                        queue.offer(new int[] {nx, ny});
                        isVisited[nx][ny] = true;
                    }
                }
            }

            for(int i = 0 ; i < R; i ++){
                for(int j = 0; j < C; j ++){
                    if(isVisited[i][j]) {
                        mMap[i][j] = 0;
                    }
                }
            }

            // printMap();
        }

        // 디버깅용 함수
        private void printMap() {
            System.out.println("====== MAP ==========");
            for(int i = 0 ; i < R ; i++){
                for(int j = 0 ; j < C; j++){
                    System.out.print(mMap[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("==============");
        }

        // 벽돌 개수 셈하는 함수
        private int countBrick() {
            int cnt = 0;
            for(int i = 0 ; i < R ; i++){
                for(int j = 0 ; j < C; j++){
                    if(mMap[i][j] > 0) cnt++;
                }
            }
            return cnt;
        }

        // 벽돌 정렬 함수
        private void reSort(int[][] map) {
            for(int c=0; c<C; c++) {
                Stack<Integer> stk = new Stack<Integer>();
                for(int r=0; r<R; r++) {
                    if(map[r][c]>0) {
                        stk.push(map[r][c]);
                        map[r][c] = 0;
                    }
                }

                int idx = R-1;
                while(!stk.isEmpty()) {
                    map[idx--][c] = stk.pop();
                }
            }
        }

        // 시뮬레이션 진행 및 결과 리턴 함수
        // 현재 주어진 케이스에 대한 결과만 반환
        public int doSimulation() {
            // printMap();
            // System.out.println("Simulation -> " + Arrays.toString(mInstruction));

            // 열 w 를 기준으로 벽돌 찾을 때까지 내려가는 로직 有
            for(int w : mInstruction) {
                int idx = 0;
                do {
                    if(mMap[idx][w] == 0) {
                        idx++;
                        continue;
                    };
                    int x = idx;
                    destroyBrick(x, w);
                    reSort(mMap);
                    break;
                }while (idx < R);
            }
            // printMap();
            return countBrick();
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("data/251026/swea5656.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t ++){
            sb.append("#").append(t);
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            CaseMaker maker = new CaseMaker(W, K);

            int[][] map = new int[H][W];
            for(int i = 0 ; i < H ; i ++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < W; j ++){
                    int ball = Integer.parseInt(st.nextToken());
                    map[i][j] = ball;
                }
            }

            // step1. CaseMaker 인스턴스를 통해 탐색해야할 경우의 수를 모두 준비한다.
            maker.getCases();
            List<int[]> cases = maker.getCaseList();

            // step2. 각 경우의 수마다 시뮬레이션 클래스인 BrickDestroyer 의 인스턴스를 통해 시뮬레이션을 진행. 
            int minVal = Integer.MAX_VALUE;
            for(int[] instruction : cases){
                BrickDestroyer destroyer = new BrickDestroyer(instruction, map);
                int cnt = destroyer.doSimulation();
                minVal = Math.min(minVal, cnt);
            }
            sb.append(" ").append(minVal).append("\n"); // 최소 값을 리턴

        }
        System.out.println(sb.toString()); // 결과 출력
    }
}
