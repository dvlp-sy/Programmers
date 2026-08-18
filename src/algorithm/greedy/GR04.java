package algorithm.greedy;

import java.util.Arrays;

/**
 * 구명보트
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42885">(바로가기)</a>
 */
public class GR04 {

    public static void main(String[] args) {
        int[] people = { 70, 50, 80, 50 };
        int limit = 100;
        GR04 gr04 = new GR04();
        System.out.println(gr04.solution(people, limit));
    }
    public int solution(int[] people, int limit) {
        int answer = 0;
        int len = people.length;
        Arrays.sort(people);

        int i = 0;
        for (int j = len - 1 ; j >= 0 ; j--) {
            // i가 j보다 커지면 종료
            if (i > j) continue;

            // 가장 가벼운 사람과 함께 탑승할 수 없다면 혼자 탑승
            if (i == j || people[i] + people[j] <= limit) {
                i++;
            }

            answer++;
        }

        return answer;
    }
}
