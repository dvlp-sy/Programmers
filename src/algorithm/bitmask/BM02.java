package algorithm.bitmask;

/**
 * 비밀 코드 해독
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/388352">(바로가기)</a>
 */
public class BM02 {

    private static int answer = 0;
    private static final int n = 10;

    private static int[] qMask;

    private static final int[][] q = {
            { 1, 2, 3, 4, 5 },
            { 6, 7, 8, 9, 10 },
            { 3, 7, 8, 9, 10 },
            { 2, 5, 7, 9, 10 },
            { 3, 4, 5, 6, 7 }
    };
    private static final int[] ans = { 2, 3, 4, 3, 3 };

    public static void main(String[] args) {
        BM02 bm02 = new BM02();
        System.out.println(bm02.solution());
        answer = 0;
        System.out.println(bm02.solution2());
    }

    private int solution2() {
        qMask = new int[q.length];
        // 비트 압축 -> [1, 3, 5, 6, 7] = 1110101
        for (int i = 0 ; i < q.length ; i++) {
            for (int num : q[i]) {
               qMask[i] = qMask[i] | (1 << (num - 1));
            }
        }
        bitmask(0, n, new int[5]);
        return answer;
    }

    private void bitmask(int depth, int n, int[] seq) {
        if (depth == 5) {
            int mask = 0;
            for (int i = 0 ; i < 5 ; i++) {
                mask = mask | (1 << (seq[i] - 1));
            }
            for (int i = 0 ; i < qMask.length ; i++) {
                if (Integer.bitCount(mask & qMask[i]) != ans[i]) {
                    return ;
                }
            }
            answer++;
            return ;
        }

        int start = 1;
        if (depth > 0) {
            start = seq[depth - 1] + 1;
        }
        for (int i = start ; i <= n ; i++) {
            seq[depth] = i;
            bitmask(depth + 1, n, seq);
        }
    }

    private int solution() {
        bruteForce(0, n, new int[5]);
        return answer;
    }

    private void bruteForce(int depth, int n, int[] seq) {
        if (depth == 5) {
            for (int k = 0 ; k < q.length ; k++) {
                int cnt = 0;
                for (int i = 0 ; i < 5 ; i++) {
                    for (int j = 0 ; j < 5 ; j++) {
                        if (seq[i] == q[k][j]) {
                            cnt++;
                        }
                    }
                }
                if (cnt != ans[k]) {
                    return ;
                }
            }
            answer++;
            return ;
        }

        int start = 1;
        if (depth > 0) {
            start = seq[depth - 1] + 1;
        }

        for (int i = start ; i <= n ; i++) {
            seq[depth] = i;
            bruteForce(depth + 1, n, seq);
        }
    }
}
