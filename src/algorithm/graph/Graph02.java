package algorithm.graph;

import java.util.Arrays;

/**
 * 순위
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/49191">(바로가기)</a>
 */
public class Graph02 {

    private static boolean[] visited;
    private static int[][] graph;

    public static void main(String[] args) {
        int n = 5;
        int[][] results = {
                {4, 3},
                {4, 2},
                {3, 2},
                {1, 2},
                {2, 5}
        };
        Graph02 graph02 = new Graph02();
        System.out.println(graph02.solution(n, results));
    }

    public int solution(int n, int[][] results) {
        int answer = 0;

        visited = new boolean[n+1];
        graph = new int[n+1][n+1];

        for (int[] r : results ) {
            graph[r[0]][r[1]] = 1;
        }

        for (int p = 1 ; p <= n ; p++) {
            Arrays.fill(visited, false);
            int lower = dfs1(p, n);

            Arrays.fill(visited, false);
            int upper = dfs2(p, n);

            // 순위를 정확히 매길 수 있는 경우
            if (lower + upper == n - 1) {
                answer++;
            }
        }

        return answer;
    }

    private int dfs1(int current, int n) {
        int count = 0;
        for (int next = 1 ; next <= n ; next++) {
            if (!visited[next] && graph[current][next] == 1) {
                visited[next] = true;
                count += dfs1(next, n) + 1;
            }
        }

        return count;
    }

    private int dfs2(int current, int n) {
        int count = 0;
        for (int next = 1 ; next <= n ; next++) {
            if (!visited[next] && graph[next][current] == 1) {
                visited[next] = true;
                count += dfs2(next, n) + 1;
            }
        }
        return count;
    }
}
