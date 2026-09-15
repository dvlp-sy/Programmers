package algorithm.dp;

/**
 * 3 x n 타일링
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12902">(바로가기)</a>
 */
public class Dp12902 {

    public static void main(String[] args) {
        int n = 8;
        Dp12902 dp12902 = new Dp12902();
        System.out.println(dp12902.solution(n));
    }

    public int solution(int n) {
        int mod = 1000000007;
        long[] dp = new long[n + 1];
        dp[2] = 3;

        // dp[i] = 3 * dp[i - 2] + 2 * dp[i - 4] + ... + 2
        for (int i = 4 ; i <= n ; i += 2) {
            dp[i] = 2;
            for (int j = i - 2 ; j >= 0 ; j -= 2) {
                if (j == i - 2) {
                    dp[i] += 3 * dp[j];
                } else {
                    dp[i] += 2 * dp[j];
                }
            }

            dp[i] %= mod;
        }

        return (int) dp[n];
    }
}
