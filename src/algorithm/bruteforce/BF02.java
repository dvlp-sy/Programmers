package algorithm.bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 모의고사
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42840">(바로가기)</a>
 */
public class BF02 {

    public static void main(String[] args) {
        int[] answers = { 1, 2, 3, 4, 5 };
        BF02 bf02 = new BF02();
        System.out.println(Arrays.toString(bf02.solution(answers)));
    }

    public int[] solution(int[] answers) {
        int[] A = { 1, 2, 3, 4, 5 };
        int[] B = { 2, 1, 2, 3, 2, 4, 2, 5 };
        int[] C = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };

        int size = answers.length;

        int[] score = { 0, 0, 0 };
        for (int i = 0 ; i < size ; i++) {
            if (A[i % 5] == answers[i]) {
                score[0]++;
            }
            if (B[i % 8] == answers[i]) {
                score[1]++;
            }
            if (C[i % 10] == answers[i]) {
                score[2]++;
            }
        }

        int maxScore = Math.max(Math.max(score[0], score[1]), score[2]);
        List<Integer> result = new ArrayList<>();
        for (int i = 0 ; i < 3 ; i++) {
            if (score[i] == maxScore) {
                result.add(i + 1);
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
