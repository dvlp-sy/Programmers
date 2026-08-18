package algorithm.greedy;

import java.util.Arrays;

/**
 * 단속카메라
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42884">(바로가기)</a>
 */
public class GR05 {

    public static void main(String[] args) {
        int[][] routes = {
                { -20, -15 },
                { -14, -5 },
                { -18, -13 },
                { -5,- 3 }
        };
        GR05 gr05 = new GR05();
        System.out.println(gr05.solution(routes));
    }

    public int solution(int[][] routes) {
        // 고속도로 나간 지점 기준 오름차순 정렬
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));

        int answer = 1;
        int loc = routes[0][1];
        for (int[] route : routes) {
            if (loc < route[0] || loc > route[1]) {
                answer++;
                loc = route[1];
            }
        }

        return answer;
    }
}
