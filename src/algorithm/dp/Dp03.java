package algorithm.dp;

import java.util.Arrays;

/**
 * 등굣길
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42898">(바로가기)</a>
 */
public class Dp03 {

    private static final long D = 1000000007;

    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};
        Dp03 dp03 = new Dp03();
        System.out.println(dp03.solution(m, n, puddles));
    }

    public int solution(int m, int n, int[][] puddles) {
        // dp -> 경로 개수 저장
        long[][] dp = new long[n+1][m+1];

        for (int i = 0 ; i <= n ; i++) {
            Arrays.fill(dp[i], 0);
        }

        // 시작 지점 및 물에 잠긴 지역 표시
        dp[1][1] = 1;
        for (int[] puddle : puddles) {
            dp[puddle[1]][puddle[0]] = -1;
        }

        for (int i = 1 ; i <= n ; i++) {
            for (int j = 1 ; j <= m ; j++) {
                // 시작점은 건너뛴다
                if (i == 1 && j == 1) continue;

                // 물에 잠긴 지역인 경우 0으로 변경 후 건너뛴다
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }

                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % D;
            }
        }

        return (int) dp[n][m];
    }
}
