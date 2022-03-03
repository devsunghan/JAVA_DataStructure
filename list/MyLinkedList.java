package list;

public class MyLinkedList<T> implements IList<T> {

    private int size;
    private Node head;

    public MyLinkedList() {
        this.size = 0;
        this.head = new Node(null); // dummy head node
    }

    @Override
    public void add(T t) {
        Node curr = this.head;
        while (curr.next != null){
            curr = curr.next; // curr의 next가 null이라는 건 뒤가 없다는 것
        }
        Node node = new Node(t);
        curr.next = node;
        this.size++;
    }

    @Override
    public void insert(int index, T t) {
        if (index > this.size || index < 0) { // index와 size가 같으면 add랑 같음
            throw new IndexOutOfBoundsException();
        }
        Node prev = this.head;
        Node curr = prev.next;

        int i = 0;
        while (i++ < index) {
            prev = prev.next;
            curr = curr.next;
        }

        Node node = new Node(t, curr);
        prev.next = node;
        this.size++;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head.next = null; // next가 없으면 찾아갈 방법이 없으니 쓰레기 수집기가 수거
    }

    @Override
    public boolean delete(T t) { // 데이터가 일치하면 삭제
        Node prev = this.head;
        Node curr = prev.next;
        while (curr != null) {
            if (curr.data.equals(t)) {
                prev.next = curr.next;
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
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node prev = this.head;
        Node curr = prev.next;

        int i = 0;
        while (i++ < index) {
            prev = prev.next;
            curr = curr.next;
        }

        prev.next = curr.next;
        curr.next = null;
        this.size--;
        return true;
    }

    @Override
    public T get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node curr = this.head.next; // 여기가 첫 데이터
        int i = 0; // 노드의 변경이 있을 때만 prev 필요
        while (i++ < index) {
            curr = curr.next;
        }
        return curr.data;
    }

    @Override
    public int indexOf(T t) {
        Node curr = this.head.next;
        int index = 0;
        while (curr != null) {
            if (t.equals(curr.data)) {
                return index;
            }
            curr = curr.next;
            index++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return this.head.next == null;
    }

    @Override
    public boolean contains(T t) {
        Node curr = head.next;
        while (curr != null) {
            if (t.equals(curr.data)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size; // 하나씩 뒤지면 O(N)의 시간 소요
    }

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
