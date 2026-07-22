package algorithm.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 디스크 컨트롤러
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42627">(바로가기)</a>
 */
public class Heap02 {

    public static void main(String[] args) {
        int[][] jobs = { { 0, 3 }, { 1, 9 }, { 2, 6 } };
        Heap02 heap02 = new Heap02();
        System.out.println(heap02.solution(jobs));
    }

    class Job implements Comparable<Job> {
        int number;
        int requestedAt;
        int workTime;

        Job (int number, int requestedAt, int workTime) {
            this.number = number;
            this.requestedAt = requestedAt;
            this.workTime = workTime;
        }

        @Override
        public int compareTo(Job o) {
            if (this.workTime == o.workTime) {
                if (this.requestedAt == o.requestedAt) {
                    // 작업 번호가 작다 -> 높은 우선순위
                    return this.number - o.number;
                }
                // 요청 시각이 빠르다 -> 높은 우선순위
                return this.requestedAt - o.requestedAt;
            }
            // 소요시간이 짧다 -> 높은 우선순위
            return this.workTime - o.workTime;
        }
    }

    public int solution(int[][] jobs) {
        PriorityQueue<Job> pq = new PriorityQueue<>();
        List<Job> temp = new ArrayList<>();

        for (int i = 0 ; i < jobs.length; i++) {
            pq.add(new Job(i, jobs[i][0], jobs[i][1]));
        }

        int time = 0;
        int allReturnTime = 0;

        while (!pq.isEmpty()) {
            // 수행할 작업 선택
            Job currentJob = null;
            while (!pq.isEmpty()) {
                Job j = pq.poll();
                if (j.requestedAt <= time) {
                    currentJob = j;
                    break;
                }
                temp.add(j);
            }
            pq.addAll(temp);
            temp.clear();

            // 작업이 있다면 처리, 없다면 시간 1 증가
            if (currentJob != null) {
                time += currentJob.workTime;
                allReturnTime += time - currentJob.requestedAt;
            } else {
                time++;
            }
        }

        return allReturnTime / jobs.length;
    }
}
