package algorithm.bruteforce;

/**
 * 전력망을 둘로 나누기
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/86971">(바로가기)</a>
 */
public class BF06 {
    private int size;
    private int count;
    private boolean[][] graph;

    public static void main(String[] args) {
        int n = 9;
        int[][] wires = {
                { 1, 3 },
                { 2, 3 },
                { 3, 4 },
                { 4, 5 },
                { 4, 6 },
                { 4, 7 },
                { 7, 8 },
                { 7, 9 }
        };
        BF06 bf06 = new BF06();
        System.out.println(bf06.solution(n, wires));
    }

    private void getCount(int node) {
        for (int i = 1 ; i <= size ; i++) {
            if (graph[node][i]) {
                graph[node][i] = false;
                graph[i][node] = false;

                getCount(i);
                count++;

                graph[node][i] = true;
                graph[node][i] = true;
            }
        }
    }

    public int solution(int n, int[][] wires) {
        size = n;
        graph = new boolean[n+1][n+1];

        for (int[] wire : wires) {
            graph[wire[0]][wire[1]] = true;
            graph[wire[1]][wire[0]] = true;
        }

        int diff = 1000;
        for (int[] wire : wires) {
            // 전선 제거
            graph[wire[0]][wire[1]] = false;
            graph[wire[1]][wire[0]] = false;

            count = 1;
            getCount(1);

            diff = Math.min(Math.abs(n - 2 * count), diff);

            // 전선 복구
            graph[wire[0]][wire[1]] = true;
            graph[wire[1]][wire[0]] = true;
        }

        return diff;
    }
}
