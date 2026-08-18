package algorithm.greedy;

import java.util.Arrays;

/**
 * 큰 수 만들기
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42883">(바로가기)</a>
 */
public class GR03 {

    public static void main(String[] args) {
        String number = "4177252841";
        int k = 4;
        GR03 gr03 = new GR03();
        System.out.println(gr03.solution(number, k));
    }

    public String solution(String number, int k) {
        int len = number.length();
        char[] numberAry = number.toCharArray();                // j 인덱스
        char[] answer = Arrays.copyOfRange(numberAry, k, len);  // i 인덱스

        int nextJ = 0;
        for (int i = 0 ; i < len - k ; i++) {
            // answer[i]에 들어갈 수 있는 문자 중 가장 큰 값
            char max = 0;
            // 제약조건1 : (len - j) >= (len - k - i)
            // 제약조건2 : answer[i]에 numberAry[j]를 집어넣었다면 answer[i+1]은 numberAry[j+1]부터 탐색해야 함
            for (int j = nextJ ; j <= k + i ; j++) {
                if (numberAry[j] > max) {
                    max = numberAry[j];
                    nextJ = j + 1;
                }
            }
            answer[i] = max;
        }
        return String.valueOf(answer);
    }
}
