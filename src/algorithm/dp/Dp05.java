package algorithm.dp;

/**
 * 도둑질
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42897#">(바로가기)</a>
 */
public class Dp05 {

    public static void main(String[] args) {
        int[] money = { 1, 2, 3, 1 };
        Dp05 dp05 = new Dp05();
        System.out.println(dp05.solution(money));
    }

    public int solution(int[] money) {
        int n = money.length;

        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        // 마지막 집을 털지 않는 경우
        dp1[0] = money[0];
        dp1[1] = Math.max(money[0], money[1]);
        for (int i = 2 ; i < n - 1 ; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
        }

        // 첫 번째 집을 털지 않는 경우
        dp2[0] = 0;
        dp2[1] = money[1];
        for (int i = 2 ; i < n ; i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
        }

        return Math.max(dp1[n - 2], dp2[n - 1]);
    }
}
