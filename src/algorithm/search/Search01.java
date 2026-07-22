package algorithm.search;

/**
 * 입국심사
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43238">(바로가기)</a>
 */
public class Search01 {

    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        Search01 search01 = new Search01();
        System.out.println(search01.solution(n, times));
    }

    public long solution(int n, int[] times) {
        long high = (long) n * max(times);
        long low = 1;

        long answer = high;

        while (low <= high) {
            long mid = (low + high) / 2;
            long value = calculate(mid, times);

            if (value >= n) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }

    private long max(int[] times) {
        long max = 0;
        for (int time : times) {
            if (time > max) {
                max = time;
            }
        }
        return max;
    }

    private long calculate(long totalTime, int[] times) {
        long n = 0;
        for (int t : times) {
            n += totalTime / t;
        }
        return n;
    }
}
