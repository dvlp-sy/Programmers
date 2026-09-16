package algorithm.dp;

import java.util.Arrays;

/**
 * 거스름돈
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12907">(바로가기)</a>
 */
public class Dp12907 {

    long answer = 0;
    int MOD = 1000000007;

    long[][] dp;

    public static void main(String[] args) {
        int n = 5;
        int[] money = { 1, 2, 5 };
        Dp12907 dp12907 = new Dp12907();
        System.out.println(dp12907.solution(n, money));
    }

    public int solution(int n, int[] money) {
        // 백트래킹을 통한 완전탐색
        backtrack(0, n, money);
        System.out.println(answer);

        // Top-down DP
        dp = new long[money.length+1][n+1];
        for (long[] d : dp) {
            Arrays.fill(d, -1);
        }
        topDown(0, n, money);
        System.out.println(dp[0][n]);

        // Bottom-up DP (2차원)
        long result = bottomUp(n, money);
        System.out.println(result);

        // Bottom-up DP (1차원)
        long result2 = bottomUp2(n, money);
        return (int) result2 % MOD;
    }

    private void backtrack(int idx, int remain, int[] money) {
        if (remain < 0) {
            return ;
        }

        if (remain == 0) {
            answer++;
            return ;
        }

        for (int i = idx ; i < money.length ; i++) {
            backtrack(i, remain - money[i], money);
        }
    }

    private long topDown(int idx, int remain, int[] money) {
        if (remain < 0) {
            return 0;
        }

        if (remain == 0) {
            return 1;
        }

        if (dp[idx][remain] != -1) {
            return dp[idx][remain];
        }

        long cnt = 0;
        for (int i = idx ; i < money.length ; i++) {
            cnt += topDown(i, remain - money[i], money);
        }

        dp[idx][remain] = cnt;
        return dp[idx][remain];
    }

    private long bottomUp(int n, int[] money) {
        long[][] dp = new long[money.length+1][n+1];
        for (long[] d : dp) {
            Arrays.fill(d, 0);
        }

        for (int idx = 0; idx <= money.length; idx++) {
            dp[idx][0] = 1;
        }

        // idx는 bottom으로 갈수록 커진다
        // remain은 bottom으로 갈수록 작아진다
        for (int idx = money.length - 1 ; idx >= 0 ; idx--) {
            for (int remain = 1 ; remain <= n ; remain++) {
                // 현재 동전을 안 쓰는 경우
                dp[idx][remain] = dp[idx + 1][remain];

                // 현재 동전을 쓰는 경우
                if (remain >= money[idx]) {
                    dp[idx][remain] += dp[idx][remain - money[idx]];
                }
            }
        }
        return dp[0][n];
    }

    private long bottomUp2(int n, int[] money) {
        long[] dp = new long[n+1];

        dp[0] = 1;

        for (int coin : money) {
            for (int remain = coin ; remain <= n ; remain++) {
                dp[remain] += dp[remain - coin];
            }
        }

        return dp[n];
    }
}
