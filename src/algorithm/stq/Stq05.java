package algorithm.stq;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 다리를 지나는 트럭
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42583">(바로가기)</a>
 */
public class Stq05 {

    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7, 4, 5, 6};

        Stq05 stq05 = new Stq05();
        System.out.println(stq05.solution(bridge_length, weight, truck_weights));
    }

    private static class Truck {
        int weight;
        int time;

        Truck(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights){
        Queue<Integer> trucks = new LinkedList<>();
        for (int w : truck_weights) {
            trucks.add(w);
        }

        Queue<Truck> trucksOnBridge = new LinkedList<>();

        int cycle = 0;
        int currentWeight = 0;

        while (!trucks.isEmpty()) {
            // 현재 다리 위의 트럭 무게 업데이트
            if (!trucksOnBridge.isEmpty()) {
                for (Truck t : trucksOnBridge) {
                    t.time++;
                }
                Truck frontTruck = trucksOnBridge.peek();
                if (frontTruck.time == bridge_length) {
                    trucksOnBridge.poll();
                    currentWeight -= frontTruck.weight;
                }
            }

            // 가능하다면 트럭 추가
            int nextWeight = trucks.peek();
            if (currentWeight + nextWeight <= weight) {
                trucks.poll();
                trucksOnBridge.add(new Truck(nextWeight, 0));
                currentWeight += nextWeight;
            }

            cycle++;
        }

        return cycle + bridge_length;
    }
}
