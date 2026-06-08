package algorithm.sort;

import java.util.Arrays;

/**
 * 가장 큰 수
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42746">(바로가기)</a>
 */
public class Sort02 {

    public static void main(String[] args) {
        int[] numbers = { 0, 1, 0 };
        Sort02 sort02 = new Sort02();
        System.out.println(sort02.solution(numbers));
    }

    private static class Number implements Comparable<Number> {
        String value;

        public Number(int value) {
            this.value = String.valueOf(value);
        }

        @Override
        public int compareTo(Number o) {
            if (value.length() == o.value.length()) {
                return o.value.compareTo(value);
            }
            String s1 = value + o.value;
            String s2 = o.value + value;
            return s2.compareTo(s1);
        }
    }

    public String solution(int[] numbers) {
        Number[] sorted = new Number[numbers.length];
        for (int idx = 0; idx < numbers.length; idx++) {
            sorted[idx] = new Number(numbers[idx]);
        }

        Arrays.sort(sorted);

        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < sorted.length; idx++) {
            Number n = sorted[idx];
            if (idx == 0 && n.value.equals("0")) {
                return "0";
            }
            sb.append(n.value);
        }
        return sb.toString();
    }
}
