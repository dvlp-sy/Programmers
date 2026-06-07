package algorithm.stq;

import java.util.Arrays;
import java.util.Stack;

/**
 * 주식가격
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42584">(바로가기)</a>
 */
public class Stq06 {

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        Stq06 stq06 = new Stq06();
        System.out.println(Arrays.toString(stq06.solution(prices)));
    }

    public int[] solution(int[] prices) {
        Stack<Integer> idxStack = new Stack<>();

        int size = prices.length;
        int[] answer = new int[size];

        for (int i = 0 ; i < size ; i++) {
            // 가격 감소 -> (i - idxStack.peek())일 만큼 동일 가격 유지
            while (!idxStack.isEmpty() && prices[i] < prices[idxStack.peek()]) {
                answer[idxStack.peek()] = i - idxStack.peek();
                idxStack.pop();
            }
            idxStack.push(i);
        }

        // 스택에 남아있는 idx -> 끝까지 가격이 감소하지 않은 케이스
        while (!idxStack.isEmpty()) {
            answer[idxStack.peek()] = (size - 1) - idxStack.peek();
            idxStack.pop();
        }

        return answer;
    }
}
