package algorithm.heap;

import java.util.PriorityQueue;

/**
 * 더 맵게
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42626">(바로가기)</a>
 */
public class Heap01 {

    public static void main(String[] args) {
        int[] scoville = { 1, 2, 3, 9, 10, 12 };
        int K = 7;
        Heap01 heap01 = new Heap01();
        System.out.println(heap01.solution(scoville, K));
    }

    class Scoville implements Comparable<Scoville> {
        int value;

        public Scoville(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Scoville o) {
            return this.value - o.value;
        }
    }

    public int solution(int[] scoville, int K) {
        PriorityQueue<Scoville> pq = new PriorityQueue<>();

        for (int s : scoville) {
            pq.add(new Scoville(s));
        }

        while (!pq.isEmpty()) {
            Scoville first = pq.poll();
            // 모든 음식의 스코빌 지수가 K 이상인 경우
            if (first.value >= K) {
                return scoville.length - pq.size() - 1;
            }

            // pq에 값이 하나만 남은 경우
            if (pq.isEmpty()) {
                return -1;
            }

            Scoville second = pq.poll();
            int mixed = first.value + (second.value * 2);
            pq.add(new Scoville(mixed));
        }
        return -1;
    }
}
