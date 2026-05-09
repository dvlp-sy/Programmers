package algorithm.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 폰켓몬
 * <a href="https://programmers.co.kr/learn/courses/30/lessons/1845">(바로가기)</a>
 */
public class Hash02 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3, 4, 5};
        Hash02 hash02 = new Hash02();
        System.out.println(hash02.solution(nums));
    }

    public int solution(int[] nums) {
        int n = nums.length / 2;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        return Math.min(n, set.size());
    }
}
