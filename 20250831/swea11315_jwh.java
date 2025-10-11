package problem250831;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AXaSUPYqPYMDFASQ
public class swea11315_jwh {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/250831/swea11315.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();~

        for(int t = 1 ; t <= tCase ; t++){
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];

            for(int i = 0 ; i < n ; i ++){
                String line = br.readLine();
                for(int j = 0 ; j < n ; j ++){
                    char x = line.charAt(j);
                    int dol = x == 'o' ? 1 : 0;
                    map[i][j] = dol;
                }
            }

            boolean isEnd = false;
            for(int i = 0 ; i < n ; i ++) {
                for (int j = 0; j < n; j++) {
                    if(map[i][j]>0){
                        isEnd = omokCheck(i, j, map);
                        if(isEnd) break;
                    }
                }
                if(isEnd) break;
            }

            String resultStr = isEnd ? "YES" : "NO";
            sb.append("#").append(t).append(" ").append(resultStr).append("\n");
        }


        System.out.println(sb.toString());

    }


    static int[] dirX = {0, 1, -1 , 1};
    static int[] dirY = {1, 0, 1 , 1};
    public static boolean omokCheck(int r, int c, int[][] map){

        int color = map[r][c];
        int rowSize = map.length;
        int colSize = map[r].length;


        for(int d = 0 ; d < 4 ; d ++){
            int dr = r;
            int dc = c;
            int dx = dirX[d];
            int dy = dirY[d];

            int prevX = (dr - dx);
            int prevY = (dc - dy);

            if(prevX >= 0 && prevY >= 0 && prevX < rowSize && prevY < colSize) {
                if(map[prevX][prevY] == color) continue;
            }

            int cnt = 1;
            do {
                dr += dx;
                dc += dy;
                if(dr < 0 || dc < 0 || dr >= rowSize || dc >= colSize) break;
                if(map[dr][dc] != color) break;
                cnt++;
            }while (true);

            if(cnt > 4) return true;  // 오목... nono..
        }

        return false;
    }


}
