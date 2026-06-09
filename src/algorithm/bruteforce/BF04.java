package algorithm.bruteforce;

import java.util.Arrays;

/**
 * 카펫
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42842">(바로가기)</a>
 */
public class BF04 {

    public static void main(String[] args) {
        int brown = 10;
        int yellow = 2;
        BF04 bf04 = new BF04();
        int[] result = bf04.solution(brown, yellow);
        System.out.println(Arrays.toString(result));
    }

    public int[] solution(int brown, int yellow) {
        int[] result = { 0, 0 };

        // 전체 카펫 크기
        int size = brown + yellow;

        for (int h = 1 ; h <= Math.sqrt(size) ; h++) {
            // h가 size의 약수가 아닌 경우는 확인하지 않음
            if (size % h != 0) continue;

            int w = size / h;
            if ((w * 2 + h * 2) - 4 == brown) {
                result[0] = w;
                result[1] = h;
                return result;
            }
        }

        return result;
    }
}
