package algorithm.stq;

import java.util.Stack;

/**
 * 올바른 괄호
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12909">(바로가기)</a>
 */
public class Stq03 {

    public static void main(String[] args) {
        String s = "(()())()";
        Stq03 stq03 = new Stq03();
        System.out.println(stq03.solution(s));
    }

    boolean solution(String s) {
        Stack<Boolean> st = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                st.push(true);
            } else {
                // ')'로 시작할 수 없다
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
        }

        if (st.isEmpty()) {
            return true;
        }
        return false;
    }
}
