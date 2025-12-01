package problem251207;

import java.util.*;

public class pgm12910_jwh {

    public int[] solution(int[] arr, int divisor) {

        PriorityQueue<Integer> ansQueue = new PriorityQueue<>();

        for (int element : arr) {
            if (element % divisor == 0) {
                ansQueue.offer(element);
            }
        }

        if(!ansQueue.isEmpty()){
            int[] answer = new int[ansQueue.size()];
            int idx = 0;
            while(!ansQueue.isEmpty()){
                int x = ansQueue.poll();
                answer[idx++] = x;
            }
            return answer;

        } else {
            return new int[] { -1 };
        }
    }

    public static void main(String[] args) {

        pgm12910_jwh t = new pgm12910_jwh();

        int[] arr = new int[] { 5, 9, 7, 10 };
        int div = 5;
        int[] ans = t.solution(arr, div);
        System.out.println(Arrays.toString(ans));

        // [5, 9, 7, 10]	5	[5, 10]

    }
}
