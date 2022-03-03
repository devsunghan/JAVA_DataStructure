package list;

import java.util.Arrays;

public class MyArrayList<T> implements IList<T> {

    private static final int DEFAULT_SIZE = 50;

    private T[] elements; // Array T 선언
    private int size;

    public MyArrayList() {
        this.size = 0;
        this.elements = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T t) {
        if (this.size == this.elements.length) {
            this.elements = Arrays.copyOf(this.elements, this.size * 2);
            // copyOf는 자바에서 제공하는 메소드다. 첫번째 매개변수를 집어넣은 두번째 매개변수 크기의 배열을 만든다.
        }
        this.elements[this.size++] = t;
    }

    @Override
    public void insert(int index, T t) {
        if (this.size == this.elements.length) {
            this.elements = Arrays.copyOf(this.elements, this.size * 2);
        }
        for (int i = index; i < this.size; i++) {
            this.elements[i + 1] = this.elements[i];
        }
        this.elements[index] = t;
        this.size++;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.elements = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public boolean delete(T t) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(t)) {
                for (int j = i; i < this.size - 1; j++) {
                    this.elements[j] = this.elements[j + 1];
                }
                this.size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByIndex(int index) {
        if (index < 0 || index > this.size - 1) {
            return false;
        }
        for (int i = index; i < this.size - 1; i++) { // 하나씩 당겨오니 size가 50이고 삭제할 데이터의 인덱스가
            this.elements[i] = this.elements[i + 1]; // 50일 경우, 51을 참조하면 에러가 나니 size - 1 을 한다.
        }
        this.size--;
        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return this.elements[index];
    }

    @Override
    public int indexOf(T t) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(t)) {
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
    public boolean contains(T t) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }
}
