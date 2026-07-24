package algorithm.graph;

import java.util.*;

/**
 * 가장 먼 노드
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/49189">(바로가기)</a>
 */
public class Graph01 {

    public static void main(String[] args) {
        int n = 6;
        int[][] vertex = {
                {3, 6},
                {4, 3},
                {3, 2},
                {1, 3},
                {1, 2},
                {2, 4},
                {5, 2}
        };
        Graph01 graph01 = new Graph01();
        System.out.println(graph01.solution(n, vertex));
    }

    public int solution(int n, int[][] edge) {
        List<Integer>[] graph = new List[n+1];
        for (int i = 0 ; i < n + 1 ; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int[] dist = new int[n+1];
        Arrays.fill(dist, 0);

        Queue<Integer> queue = new LinkedList<>();

        dist[1] = 1;
        queue.add(1);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                if (dist[next] == 0) {
                    dist[next] = dist[current] + 1;
                    queue.add(next);
                }
            }
        }

        int max = 0;
        int answer = 0;
        for (int d : dist) {
            if (d > max) {
                max = d;
                answer = 1;
            } else if (d == max) {
                answer++;
            }
        }

        return answer;
    }
}
