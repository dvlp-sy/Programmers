package algorithm.sort;

import java.util.Arrays;

/**
 * 최솟값 만들기
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12941">(바로가기)</a>
 */
public class Sort12941 {

    public static void main(String[] args) {
        int[] A = { 1, 4, 2 };
        int[] B = { 5, 4, 4 };
        Sort12941 sort12941 = new Sort12941();
        System.out.println(sort12941.solution(A, B));
    }

    public int solution(int[] A, int[] B) {
        int n = A.length;

        Arrays.sort(A);
        Arrays.sort(B);

        int answer = 0;
        for (int i = 0 ; i < n ; i++) {
            answer += A[i] * B[n-1-i];
        }

        return answer;
    }
}
