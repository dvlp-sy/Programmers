package algorithm.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * N으로 표현
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42895">(바로가기)</a>
 */
public class Dp01 {

    public static void main(String[] args) {
        int N = 5;
        int number = 12;
        Dp01 dp01 = new Dp01();
        System.out.println(dp01.solution(N, number));
    }

    private int solution(int N, int number) {
        List<Set<Integer>> list = new ArrayList<>();

        for (int i = 0 ; i <= 8 ; i++) {
            list.add(new HashSet<>());
        }

        int num = 0;
        for (int i = 1 ; i <= 8 ; i++) {
            num = num * 10 + N;
            list.get(i).add(num);
        }

        for (int i = 1 ; i <= 8 ; i++) {
            for (int j = 1 ; j < i ; j++) {
                // list[i] = list[j] (op) list[i - j]
                for (int v1 : list.get(j)) {
                    for (int v2 : list.get(i - j)) {
                        // 덧셈
                        list.get(i).add(v1 + v2);
                        // 뺄셈
                        list.get(i).add(v1 - v2);
                        // 곱셈
                        list.get(i).add(v1 * v2);
                        // 나눗셈
                        if (v2 != 0) {
                            list.get(i).add(v1 / v2);
                        }
                    }
                }
            }

            if (list.get(i).contains(number)) {
                return i;
            }
        }

        return -1;
    }
}
