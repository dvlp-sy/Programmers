package algorithm.dp;

import java.util.Arrays;

/**
 * 정수 삼각형
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43105">(바로가기)</a>
 */
public class Dp02 {

    public static void main(String[] args) {
        int[][] triangle = {
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}
        };
        Dp02 dp02 = new Dp02();
        System.out.println(dp02.solution(triangle));
    }

    private int solution(int[][] triangle) {
        int h = triangle.length;

        int[][] dp = new int[h+1][h+1];
        for (int i = 0 ; i <= h ; i++) {
            Arrays.fill(dp[i], 0);
        }

        for (int i = 0 ; i < h ; i++) {
            for (int j = 0 ; j < i + 1 ; j++) {
                dp[i+1][j+1] = Math.max(dp[i][j], dp[i][j+1]) + triangle[i][j];
            }
        }

        int answer = 0;
        for (int i = 0 ; i < h ; i++) {
            int current = dp[h][i];
            if (current > answer) {
                answer = current;
            }
        }

        return answer;
    }
}
