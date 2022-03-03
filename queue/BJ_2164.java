package queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_2164 {
    public static void main(String[] args) {
        // 카드를 4,3,2,1 순서로 쌓아서, 1이 제일 위에 있다고 치자
        // 첫번째 카드는 버리고, 두번째 카드는 제일 밑으로 넣는 걸
        // 카드가 한장 남을 때까지 반복

//        MyLinkedQueue<Integer> a = new MyLinkedQueue<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
//        for (int i = 0; i < n; i++) {
//            int b = sc.nextInt();
//            a.offer(b);
//        }
//
//        for (int i = 0; i < n-1; i++) {
//            int b = a.poll();
//            b = a.peek();
//            a.offer(b);
//        }
//        System.out.println(a.peek());

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.add(i+1); // 4가 들어오면 1,2,3,4
        }

        int count = 1; // 홀수면 빼고 삽입, 짝수면 버리기만
        while (queue.size() != 1) {
            int q = queue.poll();
            if (count % 2 == 0) {
                queue.offer(q);
            }
            System.out.println(count + " -> " + queue);
            count++;
        }
        System.out.println(queue);
    }
}
