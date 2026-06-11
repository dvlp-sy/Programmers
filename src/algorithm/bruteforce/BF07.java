package algorithm.bruteforce;

import java.util.Stack;

/**
 * 모음사전
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/84512">(바로가기)</a>
 */
public class BF07 {

    int location = 0;
    boolean found = false;

    char[] alphabets = { 'A', 'E', 'I', 'O', 'U' };
    Stack<Character> selected = new Stack<>();
    String wordStr;

    public static void main(String[] args) {
        String word = "I";
        BF07 bf07 = new BF07();
        System.out.println(bf07.solution(word));
    }

    private void dfs(int depth) {
        if (equal()) {
            found = true;
            return ;
        }

        if (depth == 5 || found) {
            return ;
        }

        for (char alphabet : alphabets) {
            if (!found) {
                selected.push(alphabet);
                location++;
                dfs(depth + 1);
                selected.pop();
            }

        }
    }

    private boolean equal() {
        char[] wordArray = wordStr.toCharArray();
        int length = wordArray.length;

        if (length != selected.size()) {
            return false;
        }

        for (int i = 0 ; i < length ; i++) {
            if (wordArray[i] != selected.get(i)) {
                return false;
            }
        }
        return true;
    }

    public int solution(String word) {
        wordStr = word;
        dfs(0);
        return location;
    }
}
