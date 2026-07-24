package algorithm.dfsbfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 단어 변환
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43163">(바로가기)</a>
 */
public class DfsBfs04 {

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
        DfsBfs04 dfsBfs04 = new DfsBfs04();
        System.out.println(dfsBfs04.solution(begin, target, words));
    }

    static class Word {
        String w;
        int shortest;

        Word(String w, int shortest) {
            this.w = w;
            this.shortest = shortest;
        }
    }

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int n = words.length;

        Queue<Word> queue = new LinkedList<>();
        int[] dp = new int[n];

        Arrays.fill(dp, -1);
        queue.add(new Word(begin, 0));

        while (!queue.isEmpty()) {
            Word word = queue.poll();

            // word가 target과 같다면 즉시 종료
            if (word.w.equals(target)) {
                answer = word.shortest;
                break;
            }

            // 다음 단어 (변환 가능한 단어) 추가
            for (int i = 0 ; i < n ; i++) {
                if (dp[i] == -1 && isConvertable(word.w, words[i])) {
                    dp[i] = word.shortest + 1;
                    queue.add(new Word(words[i], word.shortest + 1));
                }
            }
        }

        return answer;
    }

    private boolean isConvertable(String word1, String word2) {
        int length = word1.length();

        int difference = 0;
        for (int i = 0 ; i < length ; i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                difference++;
            }
        }

        return difference == 1;
    }
}
