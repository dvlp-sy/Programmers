package algorithm.dp;

/**
 * 보행자 천국
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/1832">(바로가기)</a>
 */
public class Dp1832 {

    private static final int MOD = 20170805;

    public static void main(String[] args) {
        int m = 3;
        int n = 6;
        int[][] cityMap = {
                { 0, 2, 0, 0, 0, 2 },
                { 0, 0, 2, 0, 1, 0 },
                { 1, 0, 0, 2, 2, 0 }
        };
        Dp1832 dp1832 = new Dp1832();
        System.out.println(dp1832.solution(m, n, cityMap));
    }

    public int solution(int m, int n, int[][] cityMap) {
        long[][] dp = new long[m][n];

        // 0행 0열 값 채우기
        for (int i = 0 ; i < m ; i++) {
            if (cityMap[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }

        for (int j = 0 ; j < n ; j++) {
            if (cityMap[0][j] == 1) {
                break;
            }
            dp[0][j] = 1;
        }

        // dp 계산
        for (int i = 1 ; i < m ; i++) {
            for (int j = 1 ; j < n ; j++) {
                // cityMap[i][j]가 통행 금지 구역 -> 통행 방법의 개수 = 0
                if (cityMap[i][j] == 1) continue;

                // 위쪽에서 오는 경우
                long case1 = 0;
                for (int k = i - 1 ; k >= 0 ; k--) {
                    if (cityMap[k][j] != 2) {
                        case1 = dp[k][j];
                        break;
                    }
                }

                // 왼쪽에서 오는 경우
                long case2 = 0;
                for (int k = j - 1 ; k >= 0 ; k--) {
                    if (cityMap[i][k] != 2) {
                        case2 = dp[i][k];
                        break;
                    }
                }

                dp[i][j] = (case1 + case2) % MOD;
            }
        }

        return (int) dp[m-1][n-1];
    }
}
