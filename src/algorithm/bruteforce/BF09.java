package algorithm.bruteforce;

/**
 * 두 원 사이의 정수 쌍
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/181187">(바로가기)</a>
 */
public class BF09 {

    public static void main(String[] args) {
        int r1 = 2;
        int r2 = 3;
        BF09 bf09 = new BF09();
        System.out.println(bf09.solution(r1, r2));
    }

    public long solution(int r1, int r2) {
        long answer = 0;

        long r1Square = (long) r1 * r1;
        long r2Square = (long) r2 * r2;

        for (int x = 1; x <= r2; x++) {
            long xSquare = (long) x * x;
            long maxY = (long) Math.floor(Math.sqrt(r2Square - xSquare));
            long minY = 0;
            if (x < r1) {
                minY = (long) Math.ceil(Math.sqrt(r1Square - xSquare));
            }

            answer += (maxY - minY + 1);
        }

        return answer * 4;
    }
}
