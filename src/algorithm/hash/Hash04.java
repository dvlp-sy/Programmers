package algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 의상
 * <a href="https://school.programmers.co.kr/learn/courses/30/parts/12077">(바로가기)</a>
 */
public class Hash04 {

    public static void main(String[] args) {
        // [["yellow_hat", "headgear"], ["blue_sunglasses", "eyewear"], ["green_turban", "headgear"]]
        String[][] clothes = {
                { "yellow_hat", "headgear" },
                { "blue_sunglasses", "eyewear" },
                { "green_turban", "headgear"}
        };
        Hash04 hash04 = new Hash04();
        System.out.println(hash04.solution(clothes));
    }

    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();

        for (String[] c : clothes) {
            map.put(c[1], map.getOrDefault(c[1], 0) + 1);
        }

        int answer = 1;
        for (String key : map.keySet()) {
            answer *= map.get(key) + 1;
        }

        return answer - 1;
    }
}
