package algorithm.stq;

import java.util.Arrays;
import java.util.Stack;

/**
 * 기능개발
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42586">(바로가기)</a>
 */
public class Stq02 {

    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        Stq02 stq02 = new Stq02();
        System.out.println(Arrays.toString(stq02.solution(progresses, speeds)));
    }

    public int[] solution(int[] progresses, int[] speeds) {
        Stack<Integer> st = new Stack<>();

        int size = progresses.length;
        int[] requiredDays = new int[size];

        for (int i = 0 ; i < size ; i++) {
            int q = (100 - progresses[i]) / speeds[i];
            int r = (100 - progresses[i]) % speeds[i];

            if (r == 0) {
                requiredDays[i] = q;
            } else {
                requiredDays[i] = q + 1;
            }
        }

        int maxDay = requiredDays[0];
        st.push(1);

        for (int i = 1 ; i < size ; i++) {
            if (requiredDays[i] > maxDay) {
                st.push(1);
                maxDay = requiredDays[i];
            } else {
                int current = st.pop();
                st.push(current + 1);
            }
        }
        return st.stream().mapToInt(i -> i).toArray();
    }
}
