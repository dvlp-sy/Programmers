package algorithm.sort;

import java.util.Arrays;

/**
 * H-Index
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42747">(바로가기)</a>
 */
public class Sort03 {

    public static void main(String[] args) {
        int[] citations = { 3, 0, 6, 1, 5 };
        Sort03 sort03 = new Sort03();
        System.out.println(sort03.solution(citations));
    }

    public int solution(int[] citations) {
        int size = citations.length;
        Arrays.sort(citations);

        int maxH = 0;
        for (int i = 0 ; i < size ; i++) {
            int h = Math.min(citations[i], size - i);
            if (h > maxH) {
                maxH = h;
            }
        }

        return maxH;
    }
}
