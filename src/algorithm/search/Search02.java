package algorithm.search;

import java.io.IOException;
import java.util.Arrays;

/**
 * 징검다리
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43236">(바로가기)</a>
 */
public class Search02 {

    public static void main(String[] args) throws IOException {
        int distance = 25;
        int[] rocks = { 2, 4, 11, 14, 17, 21 };
        int n = 2;
        Search02 search = new Search02();
        System.out.println(search.solution(distance, rocks, n));
    }

    private int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);

        int low = 1;
        int high = distance;

        while (low <= high) {
            int mid = (low + high) / 2;
            // 바위 간 최소 거리를 mid라 가정 -> 제거한 바위 개수가 n보다 작거나 같으면 더 큰 최소 거리로 확인
            if (getDeletedRockCount(mid, distance, rocks) <= n) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return answer;
    }

    private int getDeletedRockCount(int mid, int distance, int[] rocks) {
        int current = 0;
        int count = 0;

        for (int rock : rocks) {
            if (rock - current < mid) {
                // 바위 간 거리가 mid보다 작은 경우 바위 제거
                count++;
            }
            else {
                // 바위 간 거리가 mid보다 크거나 같다면 바위 유지
                current = rock;
            }
        }

        // 마지막 바위에서 도착지점까지의 거리가 mid보다 작은 경우 바위 제거
        if (distance - current < mid) {
            count++;
        }

        return count;
    }
}
