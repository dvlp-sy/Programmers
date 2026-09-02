package algorithm.dfsbfs;

/**
 * 리프 노드 수 최대화
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/468372">(바로가기)</a>
 */
public class DfsBfs08 {
    private static int dLimit;
    private static int sLimit;
    private static int answer = 1;

    public static void main(String[] args) {
        int dist_limit = 0;
        int split_limit = 100;
        DfsBfs08 dfsBfs08 = new DfsBfs08();
        System.out.println(dfsBfs08.solution(dist_limit, split_limit));
    }

    public int solution(int dist_limit, int split_limit) {
        dLimit = dist_limit;
        sLimit = split_limit;

        dfs(1, 1, 1, 0);
        return answer;
    }

    // cur : 현재 깊이에서 분배 가능한 노드 수
    // used : 현재 깊이까지 사용한 분배 노드 수
    // split : 현재 깊이까지 분배도
    // leaf : 현재 깊이까지 확정된 리프 노드 수
    private void dfs(long cur, long used, long split, long leaf) {
        if (used > dLimit) {
            return ;
        }

        answer = (int) Math.max(answer, leaf + cur);

        // 2개 or 3개로 분배
        for (int child = 2; child <= 3; child++) {
            long nextSplit = split * child;

            // 분배도 한도 초과 시 건너뛴다
            if (nextSplit > sLimit) continue;

            long nextNodes = cur * child;    // 다음 깊이에서 분배 가능한 노드 수
            long remain = dLimit - used;     // 남은 분배 노드 한도 안에서 가장 큰 노드 수
            long nextCur = Math.min(nextNodes, remain); // 선택한 분배 노드 수

            // 분배 노드가 아닌 노드는 리프로 확정
            long nextLeaf = leaf + (nextNodes - nextCur);

            dfs(nextCur, used + nextCur, nextSplit, nextLeaf);
        }
    }
}
