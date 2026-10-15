package algorithm.string;

/**
 * JadenCase 문자열 만들기
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12951">(바로가기)</a>
 */
public class String12951 {

    public static void main(String[] args) {
        String s = "3people unFollowed me";
        String12951 string12951 = new String12951();
        System.out.println(string12951.solution(s));
    }

    public String solution(String s) {
        char[] ary = s.toLowerCase().toCharArray();
        int n = ary.length;

        StringBuilder sb = new StringBuilder();

        if (ary[0] == ' ') {
            sb.append(ary[0]);
        } else {
            sb.append(Character.toUpperCase(ary[0]));
        }

        for (int i = 1 ; i < n ; i++) {
            if (ary[i-1] == ' ') {
                sb.append(Character.toUpperCase(ary[i]));
            } else {
                sb.append(ary[i]);
            }
        }

        return sb.toString();
    }
}
