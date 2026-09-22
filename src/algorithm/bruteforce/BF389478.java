package algorithm.bruteforce;

/**
 * 택배 상자 꺼내기
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/389478">(바로가기)</a>
 */
public class BF389478 {

    public static void main(String[] args) {
        int n = 22;
        int w = 6;
        int num = 8;
        BF389478 bf389478 = new BF389478();
        System.out.println(bf389478.solution(n, w, num));
    }

    public int solution(int n, int w, int num) {
        int[] h = new int[w];

        int height = n / w;
        int r = n % w;

        for (int i = 0 ; i < w ; i++) {
            h[i] = height;
        }

        for (int i = 0 ; i < r ; i++) {
            if ((height % 2) == 0) {
                h[i]++;
            } else {
                h[w - 1 - i]++;
            }
        }

        int h2 = (num - 1) / w;
        int r2 = (num - 1) % w;

        if ((h2 % 2) == 0) {
            return h[r2] - h2;
        } else {
            return h[w - r2 - 1] - h2;
        }
    }
}
