package algorithm.dfsbfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 지게차와 크레인
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/388353">(바로가기)</a>
 */
public class DfsBfs09 {
    int[] xDelta = { 1, -1, 0, 0 };
    int[] yDelta = { 0, 0, 1, -1 };

    Queue<Pair> queue = new LinkedList<>();

    private static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(String[] storage, String[] requests) {
        int m = storage.length;
        int n = storage[0].length();

        int[][] map = new int[m+2][n+2];
        boolean[][] visited = new boolean[m+2][n+2];

        for (int i = 1 ; i < m + 1 ; i++) {
            for (int j = 1; j < n + 1 ; j++) {
                // 테두리 부분은 2로 표시
                if (i == 1 || i == m || j == 1 || j == n) {
                    map[i][j] = 2;
                } else {
                    map[i][j] = 1;
                }
            }
        }

        int deleteCount = 0;

        for (String request : requests) {
            char target = request.charAt(0);
            int requestType = request.length();

            // 테두리 업데이트
            bfs(map, visited, m, n);

            // 지게차
            if (requestType == 1) {
                for (int i = 0 ; i < m ; i++) {
                    for (int j = 0 ; j < n ; j++) {
                        // 빈 공간, 내부 공간은 확인 X
                        if (map[i+1][j+1] == 0 || map[i+1][j+1] == 1) continue;

                        // 테두리에서 컨테이너 제거
                        if (map[i+1][j+1] == 2 && storage[i].charAt(j) == target) {
                            map[i+1][j+1] = 0;
                            deleteCount++;
                        }
                    }
                }
            }
            // 크레인
            else {
                for (int i = 0 ; i < m ; i++) {
                    for (int j = 0 ; j < n ; j++) {
                        // 모든 target 제거
                        if (map[i+1][j+1] != 0 && storage[i].charAt(j) == target) {
                            map[i+1][j+1] = 0;
                            deleteCount++;
                        }
                    }
                }
            }

        }

        return n * m - deleteCount;
    }

    private void bfs(int[][] map, boolean[][] visited, int m, int n) {
        for (boolean[] v : visited) {
            Arrays.fill(v, false);
        }

        queue.add(new Pair(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nextX = current.x + xDelta[i];
                int nextY = current.y + yDelta[i];

                if (nextX < 0 || nextX > m + 1 || nextY < 0 || nextY > n + 1) continue;

                if (visited[nextX][nextY]) continue;

                if (map[nextX][nextY] >= 1) {
                    // 테두리인데 테두리로 표시되지 않은 경우
                    map[nextX][nextY] = 2;
                } else {
                    // 외부와 연결된 빈 공간인 경우
                    queue.add(new Pair(nextX, nextY));
                }
                visited[nextX][nextY] = true;
            }
        }
    }
}
