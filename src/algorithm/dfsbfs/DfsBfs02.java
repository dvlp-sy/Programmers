package algorithm.dfsbfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 게임 맵 최단거리
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/1844">(바로가기)</a>
 */
public class DfsBfs02 {
    private static final int[] xDelta = { 1, -1, 0, 0 };
    private static final int[] yDelta = { 0, 0, 1, -1 };

    public static void main(String[] args) {
        int[][] maps = {
                { 1, 0, 1, 1, 1 },
                { 1, 0, 1, 0, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 1, 0, 1 },
                { 0, 0, 0, 0, 1 }
        };
        DfsBfs02 dfsBfs02 = new DfsBfs02();
        System.out.println(dfsBfs02.solution(maps));
    }

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        int[][] dist = new int[n][m];
        Queue<Pair> queue = new LinkedList<>();

        dist[0][0] = 1;
        queue.add(new Pair(0, 0));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int x = current.x;
            int y = current.y;

            // 상대 팀 진영 도착 시 최단거리 반환
            if (x == n - 1 && y == m - 1) {
                return dist[x][y];
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nextX = x + xDelta[i];
                int nextY = y + yDelta[i];

                // 맵을 벗어나는 경우
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
                // 벽으로 막히거나 이미 거리 계산이 완료된 경우
                if (maps[nextX][nextY] == 0 || dist[nextX][nextY] > 0) continue;

                dist[nextX][nextY] = dist[x][y] + 1;
                queue.add(new Pair(nextX, nextY));
            }
        }

        return -1;
    }
}
