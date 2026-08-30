package algorithm.dfsbfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 바이러스 파이프
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/468373">(바로가기)</a>
 */
public class DfsBfs07 {

    private static List<int[]> sequences;

    public static void main(String[] args) {
        int n = 10;
        int infection = 1;
        int[][] edges = {
                { 1, 2, 1 },
                { 1, 3, 1 },
                { 1, 4, 3 },
                { 1, 5, 2 },
                { 5, 6, 1 },
                { 5, 7, 1 },
                { 2, 8, 3 },
                { 2, 9, 2 },
                { 9, 10, 1 }
        };
        int k = 2;
        DfsBfs07 dfsBfs07 = new DfsBfs07();
        System.out.println(dfsBfs07.solution(n, infection, edges, k));
    }

    public int solution(int n, int infection, int[][] edges, int k) {
        int answer = 0;

        int[][] graph = new int[n + 1][n + 1];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }

        sequences = new LinkedList<>();
        getAllCase(0, k, new int[k]);

        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        for (int[] seq : sequences) {
            Arrays.fill(visited, false);
            visited[infection] = true;

            for (int type : seq) {
                // 이미 방문한 노드 큐에 추가
                for (int i = 1 ; i <= n ; i++) {
                    if (visited[i]) {
                        queue.add(i);
                    }
                }
                while (!queue.isEmpty()) {
                    int current = queue.poll();

                    for (int next = 1 ; next < n+1 ; next++) {
                        if (!visited[next] && graph[current][next] == type) {
                            queue.add(next);
                            visited[next] = true;
                        }
                    }
                }
            }

            int sum = 0;
            for (boolean v : visited) {
                if (v) sum ++;
            }
            if (sum > answer) {
                answer = sum;
            }
        }

        return answer;
    }

    private void getAllCase(int idx, int k, int[] c) {
        if (idx == k) {
            sequences.add(Arrays.copyOfRange(c, 0, k));
            return ;
        }
        for (int type = 1 ; type <= 3 ; type++) {
            // 연속으로 같은 타입이 오는 경우는 건너뛴다
            if (idx > 0 && c[idx - 1] == type) continue;
            c[idx] = type;
            getAllCase(idx + 1, k, c);
        }
    }
}
