package algorithm.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * n + 1 카드게임
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/258707">(바로가기)</a>
 */
public class Dp258707 {

    public static void main(String[] args) {
        int coin = 4;
        int[] cards = { 3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4 };
        Dp258707 dp258707 = new Dp258707();
        System.out.println(dp258707.solution(coin, cards));
    }

    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int init = n / 3;

        Set<Integer> hand = new HashSet<>();
        Set<Integer> keep = new HashSet<>();

        for (int i = 0 ; i < init ; i++) {
            hand.add(cards[i]);
        }

        int round = 1;

        for (int i = init ; i < n ; i += 2) {
            keep.add(cards[i]);
            keep.add(cards[i + 1]);

            // 코인을 사용하지 않아도 pair가 있는 경우
            int pair = findPair(hand, hand, n + 1);
            if (pair != -1) {
                hand.remove(pair);
                hand.remove(n + 1 - pair);
                round++;
                continue;
            }

            // 코인 1개를 사용할 수 있는 경우
            if (coin >= 1) {
                pair = findPair(hand, keep, n + 1);

                if (pair != -1) {
                    hand.remove(pair);
                    keep.remove(n + 1 - pair);
                    coin--;
                    round++;
                    continue;
                }
            }

            // 코인 2개를 사용할 수 있는 경우
            if (coin >= 2) {
                pair = findPair(keep, keep, n + 1);

                if (pair != -1) {
                    keep.remove(pair);
                    keep.remove(n + 1 - pair);
                    coin -= 2;
                    round++;
                    continue;
                }
            }

            break;
        }

        return round;
    }

    private int findPair(Set<Integer> first, Set<Integer> second, int target) {
        for (int card : first) {
            int pair = target - card;

            if (pair != card && second.contains(pair)) {
                return card;
            }
        }

        return -1;
    }
}
