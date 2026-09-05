package algorithm.greedy;

import java.util.Arrays;

/**
 * 요격 시스템
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/181188">(바로가기)</a>
 */
public class GR06 {

    public static void main(String[] args) {
        int[][] targets = {
                { 4, 5 },
                { 4, 8 },
                { 10, 14 },
                { 11, 13 },
                { 5, 12 },
                { 3, 7 },
                { 1, 4 }
        };
        GR06 GR06 = new GR06();
        System.out.println(GR06.solution(targets));
    }

    public int solution(int[][] targets) {
        Arrays.sort(targets, (t1, t2) -> {
            if (t1[1] == t2[1]) {
                return t1[0] - t2[0];
            }
            return t1[1] - t2[1];
        });

        int idx = 0;
        int answer = 0;
        while (idx < targets.length) {
            int value = targets[idx][1];

            while (idx < targets.length && targets[idx][0] < value) {
                idx++;
            }

            answer++;
        }

        return answer;
    }
}
