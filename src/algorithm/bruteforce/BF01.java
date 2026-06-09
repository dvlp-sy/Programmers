package algorithm.bruteforce;

/**
 * 최소 직사각형
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/86491">(바로가기)</a>
 */
public class BF01 {

    public static void main(String[] args) {
        int[][] sizes = {
                { 60, 50 },
                { 30, 70 },
                { 60, 30 },
                { 80, 40 }
        };
        BF01 bf01 = new BF01();
        System.out.println(bf01.solution(sizes));
    }

    public int solution(int[][] sizes) {
        // x가 긴 변, y가 짧은 변이라고 가정
        int maxX = 0;
        int maxY = 0;

        for (int[] size : sizes) {
            int x;
            int y;
            if (size[0] < size[1]) {
                x = size[1];
                y = size[0];
            } else {
                x = size[0];
                y = size[1];
            }

            if (x > maxX) {
                maxX = x;
            }

            if (y > maxY) {
                maxY = y;
            }

        }
        return maxX * maxY;
    }
}
