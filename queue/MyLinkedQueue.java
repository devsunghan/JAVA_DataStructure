package queue;

public class MyLinkedQueue<T> implements IQueue<T> {
// 노드 기반 큐
    private Node head;
    private Node tail;
    private int size;

    public MyLinkedQueue() {
        this.size = 0;
        this.head = new Node(null);
        this.tail = this.head; // head의 레퍼런스를 집어넣었으니 tail에서
        // 다른 레퍼런스를 집어넣기 전까진, head를 조작하는 것과 같다.
    }

    @Override
    public void offer(T data) {
        Node node = new Node(data);
        this.tail.next = node;
        this.tail = this.tail.next;
        this.size++;
    }

    @Override
    public T poll() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        Node node = this.head.next;
        this.head.next = node.next;
        node.next = null;
        this.size--;

        if (this.head.next == null) {
            this.tail = this.head;
        }

        return node.data;
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        return this.head.next.data;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.head.next = null;
        this.tail = head;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.head.next == null;
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
