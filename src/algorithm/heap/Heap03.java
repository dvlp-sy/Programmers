package algorithm.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 이중우선순위큐
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42628">(바로가기)</a>
 */
public class Heap03 {

    public static void main(String[] args) {
        String[] operations = { "I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1" };
        Heap03 heap03 = new Heap03();
        int[] result = heap03.solution(operations);
        System.out.println(Arrays.toString(result));
    }

    class Max implements Comparable<Max> {
        int value;

        Max(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Max o) {
            return o.value - value;
        }
    }

    class Min implements Comparable<Min> {
        int value;

        Min(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Min o) {
            return value - o.value;
        }
    }

    public int[] solution(String[] operations) {
        PriorityQueue<Max> maxQ = new PriorityQueue<>();
        PriorityQueue<Min> minQ = new PriorityQueue<>();
        Map<Integer, Integer> count = new HashMap<>();

        for (String op : operations) {
            if (op.startsWith("I")) {
                int value = Integer.parseInt(op.split(" ")[1]);
                maxQ.add(new Max(value));
                minQ.add(new Min(value));
                count.put(value, count.getOrDefault(value, 0) + 1);
            } else if (op.equals("D 1")) {
                while (!maxQ.isEmpty()) {
                    Max max = maxQ.poll();
                    // 삭제할 수 있는 값이 있는 경우에만 삭제
                    if (count.get(max.value) >= 1) {
                        count.put(max.value, count.get(max.value) - 1);
                        System.out.println("max: " + max.value + " " + count.get(max.value));
                        break;
                    }
                }
            } else if (op.equals("D -1")) {
                while (!minQ.isEmpty()) {
                    Min min = minQ.poll();
                    // 삭제할 수 있는 값이 있는 경우에만 삭제
                    if (count.get(min.value) >= 1) {
                        count.put(min.value, count.get(min.value) - 1);
                        System.out.println("min: " + min.value + " " + count.get(min.value));
                        break;
                    }

                }
            }
        }

        int[] result = new int[2];
        Arrays.fill(result, 0);

        while (!maxQ.isEmpty()) {
            Max max = maxQ.peek();
            if (count.get(max.value) >= 1) {
                result[0] = max.value;
                break;
            }
            maxQ.poll();
        }

        while (!minQ.isEmpty()) {
            Min min = minQ.peek();
            if (count.get(min.value) >= 1) {
                result[1] = min.value;
                break;
            }
            minQ.poll();
        }

        return result;
    }
}
