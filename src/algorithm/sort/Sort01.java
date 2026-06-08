package algorithm.sort;

import java.util.Arrays;

/**
 * K번째 수
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42748">(바로가기)</a>
 */
public class Sort01 {

    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int idx = 0; idx < commands.length; idx++) {
            int i = commands[idx][0];
            int j = commands[idx][1];
            int k = commands[idx][2];

            int[] temp = Arrays.copyOfRange(array, i - 1, j);
            Arrays.sort(temp);
            answer[idx] = temp[k - 1];
        }

        return answer;
    }
}
