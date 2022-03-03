package list;

public class MyDoubleLinkedList<T> implements IList<T> {

    private Node head;
    private Node tail;
    private int size;

    public MyDoubleLinkedList() {
        this.size = 0;
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    @Override
    public void add(T t) {
        Node last = this.tail.prev;
        Node node = new Node(t, last, tail);
        last.next = node;
        this.tail.prev = node;
        this.size++;
    }

    @Override
    public void insert(int index, T t) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node prev = null;
        Node curr = null;

        int i = 0;
        if (index < this.size / 2) {
            prev = this.head;
            curr = this.head.next;

            while (i++ < index) {
                prev = prev.next;
                curr = curr.next;
            }

        } else {
            curr = this.tail;
            prev = this.tail.prev;
            while (i++ < (this.size - index)) {
                curr = curr.prev;
                prev = prev.prev;
            }
        }
        Node node = new Node(t, prev, curr);
        prev.next = node;
        curr.prev = node;
        this.size++;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head.prev = null;
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.tail.next = null;
    }

    @Override
    public boolean delete(T t) {
        Node prev = this.head;
        Node curr = prev.next;
        for (int i = 0; i < this.size; i++) {
            if (curr.data.equals(t)) {
                prev.next = curr.next;
                curr.next.prev = prev;
                curr.prev = null;
                curr.next = null;
                this.size--;
                return true;
            }
            prev = prev.next;
            curr = curr.next;
        }
        return false;
    }

    @Override
    public boolean deleteByIndex(int index) {
        Node prev = null;
        Node curr = null;
        Node next = null;

        int i = 0;
        if (index < this.size / 2) {
            prev = this.head;
            curr = this.head.next;
            while (i++ < index) {
                prev = prev.next;
                curr = curr.next; // curr에 지우고 싶은 데이터가 들어감
            }

            prev.next = curr.next;
            curr.next.prev = prev; // 원래 curr을 가르킴
            curr.next = null;
            curr.prev = null;
        } else { // tail 에서 역으로 찾아가는 경우
            curr = this.tail.prev;
            next = this.tail;
            while (i++ < (this.size - index - 1)) { // 데이터가 있는 노드부터 시작해야 하니 -1
                next = next.prev;
                curr = curr.prev;
            }
            next.prev = curr.prev;
            curr.prev.next = next;
            curr.next = null;
            curr.prev = null;
        }
        this.size--;
        return true;
    }

    @Override
    public T get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        Node curr = null;
        if (index < this.size / 2) {  // index가 head에서 더 가까우면
            curr = this.head.next;
            while (i++ < index) {
                curr = curr.next;
            }
        } else { // index가 tail에서 더 가까우면
            curr = this.tail.prev;
            while (i++ < (this.size - index - 1)) {
                curr = curr.prev;
            }
        }
        return curr.data;
    }

    @Override
    public int indexOf(T t) {
        Node curr = head;
        for (int i = 0; i < this.size; i++) {
            curr = curr.next;
            if (curr.data != null && curr.data.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(T t) {  // O(N) -> O(N/2)
        Node head_curr = this.head.next;
        Node tail_curr = this.tail.prev;
        while (head_curr == tail_curr || head_curr.prev == tail_curr.next) {
            if (head_curr.data.equals(t) || tail_curr.data.equals(t)) {
                return true;
            }
            head_curr = head_curr.next;
            tail_curr = tail_curr.prev;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    private class Node {
        T data;
        Node prev;
        Node next;

        Node(T data) { this.data = data;}

        Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
