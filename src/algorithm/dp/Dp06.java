package algorithm.dp;

import java.util.Arrays;

/**
 * 완전범죄
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/389480">(바로가기)</a>
 */
public class Dp06 {

    int INF = 1000;

    public static void main(String[] args) {
        int[][] info = {
                { 1, 2 },
                { 2, 3 },
                { 2, 1 }
        };
        int n = 4;
        int m = 4;

        Dp06 dp06 = new Dp06();
        System.out.println(dp06.solution(info, n, m));
        System.out.println(dp06.solution2(info, n, m));
    }

    // Top-down DP
    public int solution(int[][] info, int n, int m) {
        int[][] dp = new int[info.length][121];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int answer = func(0, 0, dp, info, n, m);
        return answer >= INF ? -1 : answer;
    }

    // Bottom-up DP
    public int solution2(int[][] info, int n, int m) {
        // dp[b] = B가 남긴 흔적의 개수가 b일 때 A가 남긴 흔적의 최소 개수
        int[] dp = new int[m];
        Arrays.fill(dp, INF);

        dp[0] = 0;

        for (int[] item : info) {
            int aCost = item[0];
            int bCost = item[1];

            // 특정 item 까지 고려했을 때 B 흔적당 A가 남긴 흔적의 최소 개수
            int[] newDp = new int[m];
            Arrays.fill(newDp, INF);

            for (int b = 0; b < m; b++) {
                // A가 훔치는 경우
                if (dp[b] + aCost < n) {
                    newDp[b] = Math.min(newDp[b], dp[b] + aCost);
                }

                // B가 훔치는 경우
                if (b + bCost < m) {
                    newDp[b + bCost] = Math.min(newDp[b], dp[b]);
                }
            }

            dp = newDp;
        }

        int answer = INF;
        for (int i = 0 ; i < m ; i++) {
            answer = Math.min(answer, dp[i]);
        }
        return answer >= INF ? -1 : answer;
    }

    private int func(int i, int currentB, int[][] dp, int[][] info, int n, int m) {

        if (currentB >= m) {
            return INF;
        }

        if (i == info.length) {
            return 0;
        }

        if (dp[i][currentB] != -1) {
            return dp[i][currentB];
        }

        // A가 i번째 아이템을 훔칠 때 A가 남긴 흔적의 누적 개수
        int pickA = info[i][0] + func(i + 1, currentB, dp, info, n, m);

        // B가 i번째 아이템을 훔칠 때 A가 남긴 흔적의 누적 개수
        int pickB = func(i + 1, currentB + info[i][1], dp, info, n, m);

        int min = Math.min(pickA, pickB);
        if (min >= n) {
            return dp[i][currentB] = INF;
        }
        return dp[i][currentB] = min;
    }
}
