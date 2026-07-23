package algorithm.dfsbfs;

/**
 * 타겟넘버
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43165">(바로가기)</a>
 */
public class DfsBfs01 {
    private static int[] sNumbers;
    private static int n;
    private static int answer;

    public static void main(String[] args) {
        int[] numbers = { 1, 1, 1, 1, 1 };
        int target = 3;
        DfsBfs01 dfsBfs01 = new DfsBfs01();
        System.out.println(dfsBfs01.solution(numbers, target));
    }

    public int solution(int[] numbers, int target) {
        answer = 0;
        n = numbers.length;
        sNumbers = new int[n];

        for (int i = 0 ; i < n ; i++) {
            sNumbers[i] = numbers[i];
        }

        dfs(0, 0, target);

        return answer;
    }

    private void dfs(int idx, int current, int target) {
        if (idx == n) {
            if (current == target) {
                answer++;
            }
            return ;
        }

        dfs(idx + 1, current + sNumbers[idx], target);
        dfs(idx + 1, current - sNumbers[idx], target);
    }
}
