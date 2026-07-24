package algorithm.dfsbfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 아이템 줍기
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/87694">(바로가기)</a>
 */
public class DfsBfs05 {

    private static final int[][] map = new int[102][102];
    private static final int[][] shortest = new int[102][102];

    private static final int[] xDelta = { 1, -1, 0, 0 };
    private static final int[] yDelta = { 0, 0, 1, -1 };

    public static void main(String[] args) {
        int[][] rectangle = {
                {1, 1, 7, 4},
                {3, 2, 5, 5},
                {4, 3, 6, 9},
                {2, 6, 8, 8}
        };
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;
        DfsBfs05 dfsBfs05 = new DfsBfs05();
        System.out.println(dfsBfs05.solution(rectangle, characterX, characterY, itemX, itemY));
    }

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        // map에 직사각형 영역 표시 (테두리 1, 내부 2)
        for (int[] r : rectangle) {
            int x1 = r[0] * 2, y1 = r[1] * 2;
            int x2 = r[2] * 2, y2 = r[3] * 2;

            for (int i = x1 ; i <= x2 ; i++) {
                for (int j = y1 ; j <= y2 ; j++) {
                    // 이미 내부 영역인 경우 패스
                    if (map[i][j] == 2) continue;

                    if (i == x1 || i == x2 || j == y1 || j == y2) {
                        map[i][j] = 1;
                    } else {
                        map[i][j] = 2;
                    }
                }
            }
        }

        Queue<Pair> queue = new LinkedList<>();

        int startX = characterX * 2;
        int startY = characterY * 2;
        int endX = itemX * 2;
        int endY = itemY * 2;

        queue.add(new Pair(startX, startY));
        shortest[startX][startY] = 1;

        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            // 아이템을 만나면 종료 후 최단거리 저장
            if (p.x == endX && p.y == endY) {
                return shortest[endX][endY] / 2;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nextX = p.x + xDelta[i];
                int nextY = p.y + yDelta[i];

                if (nextX < 0 || nextX > 100 || nextY < 0 || nextY > 100) continue;

                // 이동할 수 없거나 이미 한 번 방문한 경우 스킵
                if (map[nextX][nextY] != 1 || shortest[nextX][nextY] > 0) continue;

                // 모서리 부분으로만 이동 가능
                if (map[nextX][nextY] == 1) {
                    queue.add(new Pair(nextX, nextY));
                    shortest[nextX][nextY] = shortest[p.x][p.y] + 1;
                }
            }

        }

        return answer;
    }
}
