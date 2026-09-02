package algorithm.stq;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 서버 증설 횟수
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/389479">(바로가기)</a>
 */
public class Stq07 {

    public static void main(String[] args) {
        int[] players = { 0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5 };
        int m = 3;
        int k = 5;
        Stq07 stq07 = new Stq07();
        System.out.println(stq07.solution(players, m, k));
    }

    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int serverCount = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int t = 0 ; t < 24 ; t++) {
            // 서버 반납
            while (!queue.isEmpty() && serverCount > 0) {
                if (queue.peek() > t) {
                    break;
                }
                queue.poll();
                serverCount--;
            }

            // 필요한 서버 수
            int required = players[t] / m;

            // 증설할 서버 수
            int added = required - serverCount;

            // 서버 증설
            for (int i = 0 ; i < added ; i++) {
                queue.add(t + k);
                serverCount++;
                answer++;
            }
        }

        return answer;
    }
}
