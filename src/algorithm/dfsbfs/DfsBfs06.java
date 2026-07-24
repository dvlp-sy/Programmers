package algorithm.dfsbfs;

import java.util.*;

/**
 * 여행경로
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43164">(바로가기)</a>
 */
public class DfsBfs06 {

    private static boolean found = false;
    boolean[] used;
    String[] answer;

    public static void main(String[] args) {
        String[][] tickets = {
                {"ICN", "SFO"},
                {"ICN", "ATL"},
                {"SFO", "ATL"},
                {"ATL", "ICN"},
                {"ATL","SFO"}
        };
        DfsBfs06 dfsBfs06 = new DfsBfs06();
        System.out.println(Arrays.toString(dfsBfs06.solution(tickets)));
    }

    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (a,b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });

        used = new boolean[tickets.length];
        answer = new String[tickets.length + 1];

        dfs(0, "ICN", tickets);
        answer[0] = "ICN";

        return answer;
    }

    private void dfs(int depth, String current, String[][] tickets) {
        // 정답을 찾은 경우 더 탐색하지 않는다
        if (found) return ;

        if (depth == tickets.length) {
            found = true;
            return ;
        }

        for (int i = 0 ; i < tickets.length ; i++) {
            if (!used[i] && tickets[i][0].equals(current)) {
                // 정답을 찾은 경우 더 탐색하지 않는다
                if (found) return ;

                used[i] = true;
                answer[depth + 1] = tickets[i][1];
                dfs(depth + 1, tickets[i][1], tickets);
                used[i] = false;
            }
        }
    }
}
