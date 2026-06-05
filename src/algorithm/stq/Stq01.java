package algorithm.stq;

import java.util.Arrays;
import java.util.Stack;

/**
 * 같은 숫자는 싫어
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12906">(바로가기)</a>
 */
public class Stq01 {

    public static void main(String[] args) {
        int[] arr = {1, 1, 3, 3, 0, 1, 1};
        Stq01 stq01 = new Stq01();
        System.out.println(Arrays.toString(stq01.solution(arr)));
    }

    public int[] solution(int []arr) {
        Stack<Integer> st = new Stack<>();

        for (int num : arr) {
            if (st.isEmpty() || num != st.peek()) {
                st.push(num);
            }
        }

        return st.stream().mapToInt(i -> i).toArray();
    }
}
