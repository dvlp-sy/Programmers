package algorithm.dp;

import java.util.Arrays;

/**
 * 멀리 뛰기
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12914">(바로가기)</a>
 */
public class Dp12914 {

    long[] dp;

    public static void main(String[] args) {
        int n = 5;
        Dp12914 dp12914 = new Dp12914();
        System.out.println(dp12914.solution(n));
    }

    public long solution(int n) {
        dp = new long[n+1];
//        Arrays.fill(dp, -1);
//        return topDown(n);

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3 ; i <= n ; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
        }

        return dp[n];
    }

    private long topDown(int remain) {
        if (remain < 0) {
            return 0;
        }

        if (remain == 0) {
            return 1;
        }

        if (dp[remain] != -1) {
            return dp[remain];
        }

        dp[remain] = topDown(remain - 1) + topDown(remain - 2);
        return dp[remain];
    }
}
