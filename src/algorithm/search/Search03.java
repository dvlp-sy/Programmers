package algorithm.search;

/**
 * [PCCP 기출문제] 2번 / 퍼즐 게임 챌린지
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/340212">(바로가기)</a>
 */
public class Search03 {

    public static void main(String[] args) {
        int[] diffs = { 1, 328, 467, 209, 54 };
        int[] times = { 2, 7, 1, 4, 3 };
        long limit = 1723;
        Search03 search03 = new Search03();
        System.out.println(search03.solution(diffs, times, limit));
    }

    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 1;
        for (int diff : diffs) {
            right = Math.max(right, diff);
        }

        int answer = 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (getSolveTime(mid, diffs, times) <= limit) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private long getSolveTime(int level, int[] diffs, int[] times) {
        int n = diffs.length;
        long time = 0;
        for (int i = 0 ; i < n ; i++) {
            if (i == 0 || diffs[i] <= level) {
                time += times[i];
            } else {
                long retryCnt = diffs[i] - level;
                time += retryCnt * (times[i] + times[i - 1]) + times[i];
            }
        }
        return time;
    }
}
