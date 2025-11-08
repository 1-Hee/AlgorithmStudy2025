package problem251109;

import java.util.*;
import java.io.*;

public class pgr1844_jwh {

    static class Solution {

        private static int R;
        private static int C;
        private static final int[] dx = {-1, 1, 0, 0};
        private static final int[] dy = {0, 0, -1, 1};
        private int bfs(int r, int c, int[][] maps){
            R = r;
            C = c;

            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[R][C];
            queue.offer(new int[] {0, 0, 1});
            visited[0][0] = true;

            while(!queue.isEmpty()) {
                int[] pos = queue.poll();
                int x = pos[0];
                int y = pos[1];

                if (x == (R - 1) && y == (C - 1)) {
                    return pos[2];
                }

                for(int d = 0 ; d < 4; d++){
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    if(visited[nx][ny]) continue;
                    if(maps[nx][ny] == 0) continue;

                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny, pos[2] + 1 });
                }
            }

            return -1;
        }


        public int solution(int[][] maps) {
            R = maps.length;
            C = maps[0].length;

            int answer = bfs(R, C, maps);
            return answer;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        // [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]
        int[][] maps = new int[][] {
            { 1,0,1,1,1 },
            { 1,0,1,0,1 },
            { 1,0,1,1,1 },
            { 1,1,1,0,1 },
            { 0,0,0,0,1 }
        };
        int res = solution.solution(maps);
        System.out.println(res);

    }
}
