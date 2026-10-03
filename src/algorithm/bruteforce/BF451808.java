package algorithm.bruteforce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * 숫자 야구
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/451808">(바로가기)</a>
 */
public class BF451808 {

    public static void main(String[] args) {
        int n = 5;
        BF451808 bf451808 = new BF451808();
        System.out.println(bf451808.solution(n, (num) -> {
            int answer = 1357;

            int strike = 0;
            int ball = 0;

            String numStr = String.valueOf(num);
            for (int i = 0; i < 4; i++) {
                if (numStr.charAt(i) == String.valueOf(answer).charAt(i)) {
                    strike++;
                } else if (String.valueOf(answer).contains(String.valueOf(numStr.charAt(i)))) {
                    ball++;
                }
            }

            if (strike == 0 && ball == 0) {
                return "OUT";
            }
            return strike + "S" + " " + ball + "B";
        }));
    }

    public int solution(int n, Function<Integer, String> submit) {

        boolean[] visited = new boolean[10];
        List<String> allCases = new ArrayList<>();
        getAllCases("", allCases, visited);

        for (int i = 0 ; i < n ; i++) {
            // 후보가 1개만 남으면 종료
            if (allCases.size() == 1) {
               return Integer.parseInt(allCases.get(0));
            }

            // 확인할 숫자 선택
            int num = Integer.parseInt(allCases.get(0));

            String answer = submit.apply(num);

            // 주어진 스트라이크, 볼 개수와 일치하는 후보만 남긴다
            if (answer.equals("OUT")) {
                allCases = filter(String.valueOf(num), 0, 0, allCases);
            } else {
                allCases = filter(String.valueOf(num),
                        Integer.parseInt(String.valueOf(answer.charAt(0))),
                        Integer.parseInt(String.valueOf(answer.charAt(3))),
                        allCases);
            }
        }

        return Integer.parseInt(allCases.get(0));
    }

    private List<String> filter(String answer, int strikeCnt, int ballCnt, List<String> allCases) {
        List<String> updated = new ArrayList<>();
        Set<Character> set = new HashSet<>();

        for (char s : answer.toCharArray()) {
            set.add(s);
        }

        for (String candidate : allCases) {

            int strike = 0;
            int ball = 0;

            for (int j = 0 ; j < 4 ; j++) {

                // 스트라이크 여부 확인
                if (candidate.charAt(j) == answer.charAt(j)) {
                    strike++;
                    continue;
                }

                // 볼 여부 확인
                if (set.contains(candidate.charAt(j))) {
                    ball++;
                }
            }

            if (strike == strikeCnt && ball == ballCnt) {
                updated.add(candidate);
            }
        }
        return updated;
    }

    private void getAllCases(String num, List<String> list, boolean[] visited) {
        if (num.length() == 4) {
            list.add(num);
            return ;
        }
        for (int i = 1 ; i <= 9 ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                getAllCases(num + i, list, visited);
                visited[i] = false;
            }
        }
    }
}
