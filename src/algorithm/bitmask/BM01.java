package algorithm.bitmask;

import java.util.Arrays;

/**
 * 힌트 스테이지
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/468377">(바로가기)</a>
 */
public class BM01 {

    private static int n;
    private static int m;

    public static void main(String[] args) {
        int[][] cost = {
                { 160, 140, 120, 110, 60 },
                { 290, 270, 260, 120, 10 },
                { 160, 130, 120, 60, 20 },
                { 160, 120, 80, 70, 20 },
                { 110, 70, 60, 30, 20 }
        };
        int[][] hint = {
                { 40, 2, 3 },
                { 40, 5, 3 },
                { 20, 5, 4 },
                { 50, 5, 5 }
        };
        BM01 BM01 = new BM01();
        System.out.println(BM01.solution(cost, hint));
    }

    public int solution(int[][] cost, int[][] hint) {
        int answer = Integer.MAX_VALUE;

        n = cost.length; // 스테이지 개수
        m = hint.length; // 힌트 번들 개수

        int combinations = 1 << m; // 번들을 선택하는 경우의 수
        int[] selected = new int[n];

        for (int mask = 0 ; mask < combinations ; mask++) {
            // 사용 가능한 힌트권 배열 초기화 후 힌트 비용 반환
            int totalCost = getAvailableHintCost(mask, selected, hint);

            // 최솟값 계산 (i : 스테이지, j : 힌트권 개수)
            for (int i = 0 ; i < n ; i++) {
                int maxUsage = Math.min(selected[i], n - 1);
                totalCost += cost[i][maxUsage];
            }

            if (totalCost < answer) {
                answer = totalCost;
            }
        }

        return answer;
    }

    private int getAvailableHintCost(int mask, int[] selected, int[][] hint) {
        int sum = 0;
        Arrays.fill(selected, 0);
        for (int i = 0 ; i < m ; i++) {
            if ((mask & (1 << i)) != 0) {
                sum += hint[i][0];
                for (int j = 1 ; j < hint[i].length ; j++) {
                    selected[hint[i][j] - 1]++;
                }
            }
        }
        return sum;
    }
}
