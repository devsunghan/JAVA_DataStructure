package queue;

import list.MyArrayList;

import static java.lang.System.currentTimeMillis;

public class Test2 {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        MyLinkedQueue<Integer> queue = new MyLinkedQueue<>();
        for (int i = 0; i < 1000000; i++) {
            queue.offer(i);
            list.add(i);
        }
        long beforeTime = currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.println(queue.poll());
        }
        long afterTime = currentTimeMillis();
        long diffTime = afterTime - beforeTime;
        System.out.println("ms : " + diffTime);
        System.out.println();

        beforeTime = currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.println(list.get(0));
            list.deleteByIndex(0);
        }
        afterTime = currentTimeMillis();
        diffTime = afterTime - beforeTime;
        System.out.println("ms : " + diffTime);
    }
}
