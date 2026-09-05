package algorithm.deque;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 연속된 부분 수열의 합
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/178870">(바로가기)</a>
 */
public class Dq02 {

    private static Deque<Integer> window;

    public static void main(String[] args) {
        Dq02 dq02 = new Dq02();
        int[] sequence = {1, 2, 3, 4, 5};
        int k = 7;
        int[] result = dq02.solution(sequence, k);
        int[] result2 = dq02.solution2(sequence, k);
        System.out.println("[" + result[0] + ", " + result[1] + "]");
        System.out.println("[" + result2[0] + ", " + result2[1] + "]");
    }

    public int[] solution2(int[] sequence, int k) {
        int[] answer = new int[2];
        int minLength = Integer.MAX_VALUE;

        int n = sequence.length;
        window = new ArrayDeque<>();

        int sum = 0;
        for (int right = 0 ; right < n ; right++) {
            // 오른쪽에 새 원소 추가
            window.addLast(right);
            sum += sequence[right];

            // sum 보다 크면 왼쪽 값 제거
            while (!window.isEmpty() && sum > k) {
                sum -= sequence[window.pollFirst()];
            }

            if (sum == k) {
                if (window.isEmpty()) continue;

                int currentLength = right - window.peekFirst();
                if (currentLength < minLength) {
                    minLength = currentLength;
                    answer[0] = window.peekFirst();
                    answer[1] = right;
                }
            }
        }

        return answer;
    }

    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int minLength = Integer.MAX_VALUE;

        int n = sequence.length;

        int left = 0;
        int right = 0;

        int sum = sequence[0];

        while (left <= right && right < n) {
            if (sum == k) {
                if (right - left < minLength) {
                    minLength = right - left;
                    answer[0] = left;
                    answer[1] = right;
                }
                sum -= sequence[left];
                left++;
            } else if (sum > k) {
                sum -= sequence[left];
                left++;
            } else {
                right++;
                if (right < n) {
                    sum += sequence[right];
                }
            }
        }

        return answer;
    }
}
