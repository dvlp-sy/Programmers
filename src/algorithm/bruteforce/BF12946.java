package algorithm.bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 하노이의 탑
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12946">(바로가기)</a>
 */
public class BF12946 {

    List<List<Integer>> result;

    public static void main(String[] args) {
        int n = 3;
        BF12946 bf12946 = new BF12946();
        Arrays.stream(bf12946.solution(n)).forEach(row -> {
            System.out.println(Arrays.toString(row));
        });
    }

    public int[][] solution(int n) {
        result = new ArrayList<>();
        move(n, 1, 3, 2);
        return result.stream()
                .map(list -> list.stream()
                        .mapToInt(Integer::intValue)
                        .toArray())
                .toArray(int[][]::new);
    }

    private void move(int n, int from, int to, int via) {
        if (n == 1) {
            result.add(new ArrayList<>(Arrays.asList(from, to)));
            return ;
        }

        move(n - 1, from, via, to);
        result.add(new ArrayList<>(Arrays.asList(from, to)));
        move(n - 1, via, to, from);
    }
}
