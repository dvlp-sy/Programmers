package algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 완주하지 못한 선수
 * <a href="https://programmers.co.kr/learn/courses/30/lessons/42576">(바로가기)</a>
 */
public class Hash01 {

    public static void main(String[] args) {
            String[] participant = {"leo", "kiki", "eden"};
            String[] completion = {"eden", "kiki"};

            Hash01 hash01 = new Hash01();
            System.out.println(hash01.solution(participant, completion));
    }

    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();

        // O(n)
        for (String p : participant) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }

        // O(m)
        for (String c : completion) {
            map.put(c, map.get(c) - 1);
        }

        // O(n)
        for (String key : map.keySet()) {
            if (map.get(key) != 0) {
                answer = key;
                break;
            }
        }

        return answer;
    }
}
