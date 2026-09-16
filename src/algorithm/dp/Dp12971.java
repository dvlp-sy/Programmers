package algorithm.dp;

/**
 * 스티커 모으기(2)
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12971">(바로가기)</a>
 */
public class Dp12971 {

    public static void main(String[] args){
        int[] sticker = { 14, 6, 5, 11, 3, 9, 2, 10 };
        Dp12971 dp12971 = new Dp12971();
        System.out.println(dp12971.solution(sticker));
    }

    public int solution(int[] sticker) {
        int n = sticker.length;

        if (n == 1) {
            return sticker[0];
        }

        if (n == 2) {
            return Math.max(sticker[0], sticker[1]);
        }

        // dp[i] = i번째 스티커를 선택하는 경우 중 최댓값
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        // 첫 번째 스티커를 선택하지 않는 경우
        dp1[0] = 0;
        dp1[1] = sticker[1];

        for (int i = 2 ; i < n ; i++) {
            dp1[i] = Math.max(dp1[i-2] + sticker[i], dp1[i-1]);
        }

        // 마지막 스티커를 선택하지 않는 경우
        dp2[0] = sticker[0];
        dp2[1] = Math.max(sticker[0], sticker[1]);

        for (int i = 2 ; i < n ; i++) {
            dp2[i] = Math.max(dp2[i-2] + sticker[i], dp2[i-1]);
        }

        return Math.max(dp1[n-1], dp2[n-2]);
    }
}
