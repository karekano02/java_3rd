// PriorityQueue 예제 우선순위가 높은 것부터 출력

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueEx {
    public static void main(String[] args) {
        Queue pq = new PriorityQueue();

        pq.offer(3);
        pq.offer(4);
        pq.offer(2);
        pq.offer(1);
        pq.offer(5);

        System.out.println(pq);

        Object obj = null;

        while((obj = pq.poll()) != null){
            System.out.println(obj);
        }
    }   
}
