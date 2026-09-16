package algorithm.search;

/**
 * 선입 선출 스케줄링
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12920">(바로가기)</a>
 */
public class Search12920 {

    public static void main(String[] args) {
        int n = 6;
        int[] cores = { 1, 2, 3 };
        Search12920 search12920 = new Search12920();
        System.out.println(search12920.solution(n, cores));
    }

    public int solution(int n, int[] cores) {

        // 코어 개수
        int m = cores.length;

        if (n <= m) {
            return n;
        }

        int target = n - m;
        int left = 1;
        int right = 10000 * m;

        // mid 시간 전까지 처리한 작업의 수가 n 이하면 time, completed 업데이트
        int completed = 0;
        int time = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int works = countWorks(m, mid, cores);

            if (works >= target) {
                right = mid - 1;
            } else {
                time = mid;
                completed = works;
                left = mid + 1;
            }
        }

        // 처리 가능한 코어 확인
        int remain = target - completed;
        for (int i = 0 ; i < m ; i++) {
            if (time % cores[i] == 0) {
                remain--;

                if (remain == 0) {
                    return i + 1;
                }
            }
        }

        return -1;
    }

    private int countWorks(int m, int t, int[] cores) {
        int cnt = 0;
        for (int i = 0 ; i < m ; i++) {
            cnt += (t - 1) / cores[i];
        }
        return cnt;
    }


    // O(nm)
    private int bruteForce(int n, int[] cores) {
        int sum = 0;
        int time = 0;

        int answer = 0;

        while (sum < n) {
            for (int i = 0 ; i < cores.length ; i++) {
                if (time % cores[i] == 0) {
                    sum++;

                    if (sum == n) {
                        answer = i;
                        break;
                    }
                }
            }
            time++;
        }

        return answer + 1;
    }
}
