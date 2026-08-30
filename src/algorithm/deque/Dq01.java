package algorithm.deque;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 선인장 숨기기
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/468379">(바로가기)</a>
 */
public class Dq01 {

    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        int h = 2;
        int w = 2;
        int[][] drops = {
                { 0, 0 },
                { 3, 1 },
                { 1, 3 },
                { 2, 4 },
                { 1, 1 },
                { 2, 2 },
                { 2, 3 },
                { 0, 4 }
        };
        Dq01 dq01 = new Dq01();
        System.out.println(Arrays.toString(dq01.solution(m, n, h, w, drops)));
    }

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[][] map = new int[m][n];
        for (int i = 0 ; i < m ; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }
        for (int i = 0 ; i < drops.length ; i++) {
            map[drops[i][0]][drops[i][1]] = i + 1;
        }

        for (int j = 0 ; j < n ; j++) {
            // 최솟값이 될 가능성이 있는 인덱스만 유지
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0 ; i < m ; i++) {
                // (i - h)는 윈도우에 포함되지 않는 왼쪽 밖 인덱스를 의미
                while (!deque.isEmpty() && deque.peekFirst() <= i - h) {
                    deque.pollFirst();
                }
                // 새로 들어올 값 map[i][j]보다 크거나 같은 값 제거
                while (!deque.isEmpty() && map[deque.peekLast()][j] >= map[i][j]) {
                    deque.pollLast();
                }
                // 현재 인덱스 추가
                deque.addLast(i);

                // 슬라이딩 윈도우가 완성될 때마다 값 업데이트
                if (i >= h - 1) {
                    map[i - h + 1][j] = map[deque.peekFirst()][j];
                }
            }
        }

        int max = 0;
        int[] answer = { 0, 0 };

        // 가로 방향 슬라이딩 윈도우
        for (int i = 0 ; i < m - h + 1 ; i++) {
            Deque<Integer> deque = new ArrayDeque<>();
            for (int j = 0 ; j < n ; j++) {
                while (!deque.isEmpty() && deque.peekFirst() <= j - w) {
                    deque.pollFirst();
                }
                while (!deque.isEmpty() && map[i][deque.peekLast()] >= map[i][j]) {
                    deque.pollLast();
                }
                deque.addLast(j);

                // 슬라이딩 윈도우가 완성되면 최댓값인지 확인
                if (j >= w - 1) {
                   int val = map[i][deque.peekFirst()];

                    if (val == Integer.MAX_VALUE) {
                        return new int[]{i, j - w + 1};
                    }

                   if (val > max) {
                       max = val;
                       answer[0] = i;
                       answer[1] = j - w + 1;
                   }
                }
            }
        }


        return answer;
    }
}
