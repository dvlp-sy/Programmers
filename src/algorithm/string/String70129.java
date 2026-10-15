package algorithm.string;

/**
 * 이진 변환 반복하기
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/70129">(바로가기)</a>
 */
public class String70129 {

    public static void main(String[] args) {
        String s = "110010101001";
        String70129 string70129 = new String70129();
        int[] answer = string70129.solution(s);
        System.out.println(answer[0] + ", " + answer[1]);
    }

    public int[] solution(String s) {
        int cycle = 0;
        String str = s;

        // 제거된 0의 개수
        int removed = 0;

        while (!str.equals("1")) {
            char[] ary = str.toCharArray();

            // 1의 개수 세기
            int cnt = 0;
            for (char c : ary) {
                int num = c - '0';
                if (num == 1) {
                    cnt++;
                }
            }

            removed += str.length() - cnt;

            // 이진 변환
            StringBuilder newStr = new StringBuilder();
            int q = cnt;

            while (q >= 2) {
                int r = q % 2;
                newStr.append(r);
                q /= 2;
            }
            newStr.append(q);
            str = newStr.toString();

            cycle++;
        }

        int[] answer = {0, 0};
        answer[0] = cycle;
        answer[1] = removed;

        return answer;
    }
}
