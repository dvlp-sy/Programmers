package algorithm.hash;

import java.util.*;

/**
 * 베스트앨범
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42579">(바로가기)</a>
 */
public class Hash05 {

    public static void main(String[] args) {
        String[] genres = { "classic", "pop", "classic", "classic", "pop" };
        int[] plays = { 500, 600, 150, 800, 2500 };
        Hash05 hash05 = new Hash05();
        System.out.println(Arrays.toString(hash05.solution(genres, plays)));
    }

    private int[] solution(String[] genres, int[] plays) {
        int size = genres.length;
        Map<String, List<Integer>> songs = new HashMap<>();
        Map<String, Integer> playTimes = new HashMap<>();

        for (int i = 0 ; i < size ; i++) {
            songs.computeIfAbsent(genres[i], k -> new ArrayList<>()).add(i);
            playTimes.put(genres[i], playTimes.getOrDefault(genres[i], 0) + plays[i]);
        }

        // 재생 횟수 내림차순으로 장르 정렬
        List<String> sortedGenres = new ArrayList<>(playTimes.keySet());
        sortedGenres.sort((g1, g2) -> playTimes.get(g2).compareTo(playTimes.get(g1)));

        List<Integer> result = new ArrayList<>();

        for (String genre : sortedGenres) {
            List<Integer> songsInGenre = songs.get(genre);

            // 재생 횟수 내림차순으로 정렬 (재생 횟수가 같다면 고유 번호 오름차순)
            songsInGenre.sort((p1, p2) -> {
                if (plays[p1] == plays[p2]) {
                    return p1 - p2;
                }
                return Integer.compare(plays[p2], plays[p1]);
            });

            int count = 0;
            for (int song : songsInGenre) {
                if (count >= 2) break;
                result.add(song);
                count++;
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
