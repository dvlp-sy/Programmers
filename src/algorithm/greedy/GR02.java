package algorithm.greedy;

/**
 * 조이스틱
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42860">(바로가기)</a>
 */
public class GR02 {

    public static void main(String[] args) {
        String name = "JAN";
        GR02 gr02 = new GR02();
        System.out.println(gr02.solution(name));
    }

    public int solution(String name) {
        int answer = 0;
        int length = name.length();

        int move = length - 1;

        for (int i = 0 ; i < length ; i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);

            int next = i + 1;
            while (next < length && name.charAt(next) == 'A') {
                next++;
            }

            // 시작 지점에서 i까지 이동 후 왼쪽으로 돌아가서 next 에 도달하는 경우
            move = Math.min(move, 2 * i + (length - next));

            // 시작 지점에서 오른쪽으로 이동 후 왼쪽으로 돌아가서 next 에 도달하는 경우
            move = Math.min(move, i + 2 * (length - next));
        }

        answer += move;
        return answer;
    }
}
