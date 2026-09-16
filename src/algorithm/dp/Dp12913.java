package algorithm.dp;

import java.util.Arrays;

/**
 * 땅따먹기
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12913">(바로가기)</a>
 */
public class Dp12913 {

    private static int answer = 0;
    private static int n;
    private static int m;

    private static int[][] dp;

    public static void main(String[] args) {
        int[][] land = {
                { 1,2,3,5 },
                { 5,6,7,8 },
                { 4,3,2,1 }
        };
        Dp12913 dp12913 = new Dp12913();
        System.out.println(dp12913.solution(land));
    }

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;

        dp = new int[n][m];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        // backtrack(0, 0, 0, land);
        // return topDown(0, 0, land);
        // return bottomUp(land);
        return bottomUp2(land);
    }

    private void backtrack(int score, int i, int j, int[][] land) {
        if (i >= n) {
            if (score > answer) {
                answer = score;
            }
            return ;
        }

        for (int nextJ = 0 ; nextJ < m ; nextJ++) {
            if (nextJ != j) {
                backtrack(score + land[i][nextJ], i + 1, nextJ, land);
            }
        }
    }

    private int topDown(int i, int j, int[][] land) {
        if (i == n) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        for (int nextJ = 0 ; nextJ < m ; nextJ++) {
            if (i == 0 || nextJ != j) {
                dp[i][j] = Math.max(
                        dp[i][j],
                        topDown(i + 1, nextJ, land) + land[i][nextJ]
                );
            }
        }

        return dp[i][j];
    }

    private int bottomUp(int[][] land) {
        int[][] dp = new int[n][m];
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < m ; j++) {
                if (i == 0) {
                    dp[i][j] = land[i][j];
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 1 ; i < n ; i++) {
            for (int j = 0 ; j < m ; j++) {
                for (int pJ = 0 ; pJ < m ; pJ++) {
                    if (pJ == j) continue;

                    dp[i][j] = Math.max(
                            dp[i][j],
                            dp[i-1][pJ] + land[i][j]
                    );
                }
            }
        }

        int answer = 0;
        for (int value : dp[n-1]) {
            if (value > answer) {
                answer = value;
            }
        }
        return answer;
    }

    private int bottomUp2(int[][] land) {
        int[] dp = new int[m];
        int[] next = new int[m];

        for (int j = 0 ; j < m ; j++) {
            dp[j] = land[0][j];
        }

        for (int i = 1 ; i < n ; i++) {
            for (int j = 0 ; j < m ; j++) {
                for (int pJ = 0 ; pJ < m ; pJ++) {
                    if (pJ == j) continue;

                    next[j] = Math.max(next[j], dp[pJ] + land[i][j]);
                }
            }
            int[] temp = dp;
            dp = next;
            next = temp;
        }

        int answer = 0;
        for (int value : dp) {
            if (value > answer) {
                answer = value;
            }
        }

        return answer;
    }
}
