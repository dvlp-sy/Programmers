package algorithm.dfsbfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 네트워크
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43162">(바로가기)</a>
 */
public class DfsBfs03 {

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        DfsBfs03 dfsBfs03 = new DfsBfs03();
        System.out.println(dfsBfs03.solution(n, computers));
    }

    private int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        int count = 0;

        for (int i = 0 ; i < n ; i++) {
            // 이미 방문한 경우 스킵
            if (visited[i]) continue;

            queue.add(i);
            visited[i] = true;

            while (!queue.isEmpty()) {
                int current = queue.poll();
                for (int next = 0 ; next < n ; next++) {
                    // current와 연결된 노드를 큐에 추가
                    if (next != current && computers[current][next] == 1 && !visited[next]) {
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
            count++;
        }

        return count;
    }
}
