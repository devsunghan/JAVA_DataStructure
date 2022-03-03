package stack;

public class MyStack<T> implements IStack<T> {

    private int size;
    private Node head;

    public MyStack() {
        this.size = 0;
        this.head = new Node(null); // 더미
    }

    @Override
    public void push(T data) {
        Node node = new Node(data, this.head.next);
        this.head.next = node;
        this.size++;
    }

    @Override
    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        Node curr = this.head.next; // 가장 최신의 노드
        this.head.next = curr.next;
        curr.next = null;
        this.size--;
        return curr.data;
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.head.next.data; // 데이터를 빼지 않고 데이터 가져오기
    }

    private boolean isEmpty() {
        return this.head.next == null;
    }

    @Override
    public int size() {
        return this.size;
    }

    private class Node {
        T data;
        Node next;

        Node(T data) { this.data = data; }

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

    }
}
