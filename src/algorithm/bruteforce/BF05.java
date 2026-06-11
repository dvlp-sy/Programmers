package algorithm.bruteforce;

/**
 * 피로도
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/87946">(바로가기)</a>
 */
public class BF05 {

    private boolean[] visited;
    private int size;
    private int maxCount = 0;

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {
                { 80, 20 },
                { 50, 40 },
                { 30, 10 }
        };
        BF05 bf05 = new BF05();
        System.out.println(bf05.solution(k, dungeons));
    }

    private void select(int count, int k, int[][] dungeons) {
        if (count > maxCount) {
            maxCount = count;
        }

        for (int i = 0 ; i < size ; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                select(count + 1, k - dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
    }

    public int solution(int k, int[][] dungeons) {
        size = dungeons.length;
        visited = new boolean[size];
        select(0, k, dungeons);
        return maxCount;
    }
}
