package algorithm.dfsbfs;

import java.util.*;

/**
 * [PCCP 기출문제] 2번 / 석유 시추
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/250136">(바로가기)</a>
 */
public class DfsBfs10 {

    private static int n;
    private static int m;

    private static final int[] xDelta = { 1, -1, 0, 0 };
    private static final int[] yDelta = { 0, 0, 1, -1 };

    private static List<Integer> chunkSize;
    private static int[][] chunk;
    private static Queue<Pair> queue;

    private static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int[][] land = {
                { 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 0, 0 },
                { 1, 1, 0, 0, 0, 1, 1, 0 },
                { 1, 1, 1, 0, 0, 0, 0, 0 },
                { 1, 1, 1, 0, 0, 0, 1, 1 }
        };
        DfsBfs10 dfsBfs10 = new DfsBfs10();
        System.out.println(dfsBfs10.solution(land));
    }

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;

        chunkSize = new ArrayList<>();
        queue = new LinkedList<>();

        chunk = new int[n][m];
        for (int i = 0 ; i < n ; i++) {
            Arrays.fill(chunk[i], -1);
        }

        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < m ; j++) {
                if (land[i][j] == 0 || chunk[i][j] != -1) continue;

                if (land[i][j] == 1 && chunk[i][j] == -1) {
                    int size = bfs(new Pair(i, j), chunkSize.size(), land);
                    chunkSize.add(size);
                }
            }
        }

        int answer = 0;
        for (int j = 0 ; j < m ; j++) {
            Set<Integer> chunkSet = new HashSet<>();
            int sum = 0;
            for (int i = 0 ; i < n ; i++) {
                if (chunk[i][j] == -1) continue;
                chunkSet.add(chunk[i][j]);
            }
            for (int c : chunkSet) {
                sum += chunkSize.get(c);
            }
            if (sum > answer) {
                answer = sum;
            }
        }

        return answer;
    }

    private int bfs(Pair start, int number, int[][] land) {
        queue.add(start);
        chunk[start.x][start.y] = number;

        int size = 0;

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            size++;

            for (int i = 0 ; i < 4 ; i++) {
                int nextX = current.x + xDelta[i];
                int nextY = current.y + yDelta[i];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;

                if (land[nextX][nextY] == 1 && chunk[nextX][nextY] == -1) {
                    chunk[nextX][nextY] = number;
                    queue.add(new Pair(nextX, nextY));
                }
            }
        }
        return size;
    }
}
