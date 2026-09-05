package algorithm.bruteforce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [PCCP 기출문제] 3번 / 충돌위험 찾기
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/340211">(바로가기)</a>
 */
public class BF08 {

    List<Pair>[] pathList;
    Map<String, Integer> crash = new HashMap<>();

    private static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int[][] points = {
                { 3, 2 },
                { 6, 4 },
                { 4, 7 },
                { 1, 4 }
        };
        int[][] routes = {
                { 4, 2 },
                { 1, 3 },
                { 4, 2 },
                { 4, 3 },
        };

        BF08 BF08 = new BF08();
        System.out.println(BF08.solution(points, routes));
    }

    public int solution(int[][] points, int[][] routes) {
        int m = routes.length; // 경로 개수
        pathList = new List[m];

        for (int i = 0 ; i < m ; i++) {
            pathList[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < m ; i++) {
            int n = routes[i].length;

            // 시작 경로 추가
            pathList[i].add(new Pair(
                    points[routes[i][0] - 1][0],
                    points[routes[i][0] - 1][1]));

            for (int j = 0 ; j < n - 1 ; j++) {
                int currentX = points[routes[i][j] - 1][0];
                int currentY = points[routes[i][j] - 1][1];
                int endX = points[routes[i][j+1] - 1][0];
                int endY = points[routes[i][j+1] - 1][1];

                // r 방향 이동 후 경로 추가
                while (currentX != endX) {
                    if (currentX < endX) {
                        currentX++;
                    } else {
                        currentX--;
                    }
                    pathList[i].add(new Pair(currentX, currentY));
                }

                // c 방향 이동 후 경로 추가
                while (currentY != endY) {
                    if (currentY < endY) {
                        currentY++;
                    } else {
                        currentY--;
                    }
                    pathList[i].add(new Pair(currentX, currentY));
                }
            }
        }

        int answer = 0;
        int maxTime = 0;

        for (int i = 0 ; i < m ; i++) {
            int time = pathList[i].size();
            if (time > maxTime) {
                maxTime = time;
            }
        }

        for (int t = 0 ; t < maxTime ; t++) {
            crash.clear();
            for (int i = 0 ; i < m ; i++) {
                if (t >= pathList[i].size()) continue;

                int x = pathList[i].get(t).x;
                int y = pathList[i].get(t).y;

                String key = x + ":" + y;
                crash.put(key, crash.getOrDefault(key, 0) + 1);
            }

            for (int value : crash.values()) {
                if (value > 1) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
