package problem250810;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea23795_jwh {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("data/swea23795_in.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        // = new StringTokenizer();
        int tCase = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= tCase ; t ++){
            sb.append("#").append(t).append(" ");

            int posX = -1;
            int posY = -1;
            int n = Integer.parseInt(br.readLine());
            int [][] field = new int[n][n];

            for(int i = 0; i < n ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j ++){
                    field[i][j] = Integer.parseInt(st.nextToken());
                    if(field[i][j] == 2){
                        posX = i;
                        posY = j;
                    }
                }
            }
            int x = -1;
            int y = -1;

            // left
            x = posX;
            y = posY;
            do {
                if(x-1 < 0) break;
                if(field[x-1][y] == 0){ // 빈칸?
                    field[x-1][y] = 2;
                    x--;
                } else break;
            }while (true);

            // right
            x = posX;
            y = posY;
            do {
                if(x+1 >= n) break;
                if(field[x+1][y] == 0){ // 빈칸?
                    field[x+1][y] = 2;
                    x++;
                } else break;
            }while (true);


            // top
            x = posX;
            y = posY;
            do {
                if(y+1 >= n) break;
                if(field[x][y+1] == 0){ // 빈칸?
                    field[x][y+1] = 2;
                    y++;
                } else break;
            }while (true);

            // bottom
            x = posX;
            y = posY;
            do {
                if(y-1 < 0) break;
                if(field[x][y-1] == 0){ // 빈칸?
                    field[x][y-1] = 2;
                    y--;
                } else break;
            }while (true);

            int cnt = 0;
            for(int r = 0 ; r < n ; r++){
                for(int c = 0 ; c < n; c++){
                    // System.out.print(field[r][c]+" ");
                    if(field[r][c] == 0){
                        cnt++;
                    }
                }
                // System.out.println();
            }
            sb.append(cnt).append("\n");

        }

        System.out.println(sb.toString());

    }
}
