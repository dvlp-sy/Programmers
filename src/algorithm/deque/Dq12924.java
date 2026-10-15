package algorithm.deque;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 숫자의 표현
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12924">(바로가기)</a>
 */
public class Dq12924 {

    public static void main(String[] args) {
        Dq12924 dq12924 = new Dq12924();
        int n = 15;
        System.out.println(dq12924.solution(n));
    }

    public int solution(int n) {
        int answer = 0;

        Deque<Integer> deque = new ArrayDeque<>();

        int sum = 0;
        for (int right = 1 ; right <= n ; right++) {
            deque.addLast(right);
            sum += right;

            while (!deque.isEmpty() && sum > n) {
                sum -= deque.pollFirst();
            }

            if (sum == n) {
                answer++;
            }
        }

        return answer;
    }
}
