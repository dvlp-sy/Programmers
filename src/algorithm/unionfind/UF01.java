package algorithm.unionfind;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 섬 연결하기
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42861">(바로가기)</a>
 */
public class UF01 {

    private static int[] parents;

    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {
                { 0, 1, 1 },
                { 0, 2, 2 },
                { 1, 2, 5 },
                { 1, 3, 1 },
                { 2, 3, 8 }
        };
        UF01 uf01 = new UF01();
        System.out.println(uf01.solution(n, costs));
    }

    public int solution(int n, int[][] costs) {
        // cost 기준 오름차순 정렬
        Arrays.sort(costs, Comparator.comparingInt(a -> a[2]));

        // parents 배열 초기화
        parents = new int[n];
        for (int i = 0 ; i < n ; i++) {
            parents[i] = i;
        }

        int k = 0;      // 선택한 간선 개수
        int answer = 0; // 전체 비용

        for (int[] cost : costs) {
            // 간선 개수가 n - 1 이상이 되면 종료
            if (k >= n - 1) {
                break;
            }

            // 서킷이 생기지 않도록 간선 선택
            if (union(cost[0], cost[1])) {
                k++;
                answer += cost[2];
            }
        }
        return answer;
    }

    private static int findRoot(int node) {
        if (parents[node] == node) {
            return node;
        }
        parents[node] = findRoot(parents[node]);
        return parents[node];
    }

    private static boolean union(int x, int y) {
        int rootX = findRoot(x);
        int rootY = findRoot(y);

        // 루트가 같지 않으면 사이클이 생기지 않는다
        if (rootX != rootY) {
            parents[rootX] = rootY;
            return true;
        }

        return false;
    }
}
