package algorithm.hash;

import java.util.Arrays;

/**
 * 전화번호 목록
 * <a href="https://programmers.co.kr/learn/courses/30/lessons/42577">(바로가기)</a>
 */
public class Hash03 {

    public static void main(String[] args) {
        String[] phone_book = {"119", "97674223", "1195524421"};
        Hash03 hash03 = new Hash03();
        System.out.println(hash03.solution(phone_book));
    }

    public boolean solution(String[] phone_book) {
        // O(n log n)
        Arrays.sort(phone_book);

        // O(n)
        for (int i = 0 ; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }
        return true;
    }
}
