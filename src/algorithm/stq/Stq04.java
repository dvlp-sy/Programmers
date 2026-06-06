package algorithm.stq;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 프로세스
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42587">(바로가기)</a>
 */
public class Stq04 {

    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int location = 3;

        Stq04 stq04 = new Stq04();
        System.out.println(stq04.solution(priorities, location));
    }

    private static class Process {
        int priority;
        int name;

        Process(int priority, int name) {
            this.priority = priority;
            this.name = name;
        }
    }

    public int solution(int[] priorities, int location) {
        Queue<Process> queue = new LinkedList<>();

        int size = priorities.length;

        for (int i = 0 ; i < size ; i++) {
            queue.add(new Process(priorities[i], i));
        }

        int cycle = 0;

        while (!queue.isEmpty()) {
            // 현재 프로세스 추출
            Process current = queue.poll();

            // 현재 프로세스보다 높은 우선순위를 가진 프로세스가 있는지 확인
            boolean hasHigherPriority = false;
            for (Process p : queue) {
                if (p.priority > current.priority) {
                    hasHigherPriority = true;
                    break;
                }
            }

            if (hasHigherPriority) {
                // 현재 프로세스를 큐의 뒤로 이동
                queue.add(current);
            } else {
                // 현재 프로세스 실행
                cycle++;

                // 현재 프로세스가 location과 일치하면 종료
                if (current.name == location) {
                    break;
                }
            }
        }

        return cycle;
    }
}
