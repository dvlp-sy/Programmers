package algorithm.bruteforce;

/**
 * 소수 찾기
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42839">(바로가기)</a>
 */
public class BF03 {

    public static void main(String[] args) {
        String numbers = "1234";
        BF03 bf03 = new BF03();
        System.out.println(bf03.solution(numbers));
    }

    public int solution(String numbers) {
        int count = 0;
        int size = numbers.length();
        for (int n = 0 ; n < Math.pow(10, size) ; n++) {
            // 소수가 아닌 경우 확인하지 않음
            if (!isPrime(n)) continue;

            // 소수인 경우 numbers로 n을 만들 수 있는지 확인
            char[] chars = numbers.toCharArray();
            char[] nChars = String.valueOf(n).toCharArray();

            boolean canMake = true;
            for (char c : nChars) {
                boolean found = false;
                for (int i = 0 ; i < chars.length ; i++) {
                    if (chars[i] == c) {
                        found = true;
                        chars[i] = '-'; // 사용한 숫자는 '-'로 표시
                        break;
                    }
                }
                if (!found) {
                    canMake = false;
                    break;
                }
            }

            if (canMake) {
                count++;
            }
        }

        return count;
    }

    private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2 ; i <= Math.sqrt(n) ; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
