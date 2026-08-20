package algorithm.dp;

import java.util.Arrays;

/**
 * 사칙연산
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/1843">(바로가기)</a>
 */
public class Dp04 {

    public static void main(String[] args) {
        String[] arr = { "1", "-", "3", "+", "5", "-", "8" };
        Dp04 dp04 = new Dp04();
        System.out.println(dp04.solution(arr));
    }

    public int solution(String arr[]) {
        int n = (arr.length / 2) + 1;

        int[][] maxDp = new int[n][n];
        int[][] minDp = new int[n][n];

        for (int i = 0 ; i < n ; i++) {
            Arrays.fill(maxDp[i], -10000000);
            Arrays.fill(minDp[i], 10000000);
            maxDp[i][i] = Integer.parseInt(arr[i * 2]);
            minDp[i][i] = Integer.parseInt(arr[i * 2]);
        }

        for (int i = 0 ; i < n ; i++) {
            for (int j = i ; j < n ; j++) {
                // maxDp[start][end] = start 부터 end까지의 연산 결과 중 최댓값
                // minDp[start][end] = start 부터 end까지의 연산 결과 중 최솟값
                int start = j - i;
                int end = j;

                for (int mid = start ; mid < end ; mid++) {
                    // 연산자가 덧셈인 경우
                    if (arr[mid * 2 + 1].equals("+")) {
                        maxDp[start][end] = Math.max(maxDp[start][end], maxDp[start][mid] + maxDp[mid+1][end]);
                        minDp[start][end] = Math.min(minDp[start][end], minDp[start][mid] + minDp[mid+1][end]);
                    }
                    // 연산자가 뺄셈인 경우
                    else {
                        maxDp[start][end] = Math.max(maxDp[start][end], maxDp[start][mid] - minDp[mid+1][end]);
                        minDp[start][end] = Math.min(minDp[start][end], minDp[start][mid] - maxDp[mid+1][end]);
                    }
                }
            }
        }

        return maxDp[0][n-1];
    }
}
