package problem251207;


public class pgm77485_jwh {

    private static int[][] map;
    private final int[] dx = { 0,1,0,-1 };
    private final int[] dy = { 1,0,-1,0 };
    int rotate(int r1, int c1, int r2, int c2) {
        int d = 0;                      // 현재 방향
        int x = r1;
        int y = c1;

        int prev = map[x][y];           // 첫 값 저장
        int minValue = prev;

        while (true) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 범위를 벗어나면 방향 전환
            if (nx < r1 || ny < c1 || nx > r2 || ny > c2) {
                d = (d + 1) % 4;
                continue;
            }

            // 값 이동
            int cur = map[nx][ny];
            map[nx][ny] = prev;
            prev = cur;

            minValue = Math.min(minValue, prev);

            // 이동
            x = nx;
            y = ny;

            // 출발지로 돌아오면 종료
            if (x == r1 && y == c1) break;
        }

        return minValue;
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];

        int idx = 1;
        for(int i = 0 ; i < rows; i++){
            for(int j = 0 ; j < columns; j ++){
                map[i][j] = idx++;
            }
        }

        int[] answer = new int[queries.length];

        for(int i = 0, q = queries.length; i < q ; i++){
            int r1 = queries[i][0] - 1;
            int c1 = queries[i][1] - 1;
            int r2 = queries[i][2] - 1;
            int c2 = queries[i][3] - 1;
            int value = rotate(r1, c1, r2, c2);
            answer[i] = value;
        }
        return answer;
    }


    public static void main(String[] args) {
        pgm77485_jwh t = new pgm77485_jwh();

        int r = 6;
        int c = 6;
        int[][] queries = new int[][] {
                {2, 2, 5, 4},
                {3, 3, 6, 6},
                {5, 1, 6, 3}
        };

        int[] answer = t.solution(r, c, queries);

    }
}
