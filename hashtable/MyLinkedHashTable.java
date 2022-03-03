package hashtable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyLinkedHashTable<K, V> implements IHashTable<K, V> {
    private static final int DEFAULT_BUCKET_SIZE = 1024; //2진수

    private List<Node>[] buckets;
    private int size;
    private int bucketSize;

    public MyLinkedHashTable() {
        this.buckets = new List[DEFAULT_BUCKET_SIZE];
        this.bucketSize = DEFAULT_BUCKET_SIZE;
        this.size = 0;

        for (int i = 0; i < bucketSize; i++) {
            this.buckets[i] = new LinkedList<>();
            // 초기화하지 않으면, null이어서 데이터를 못넣
        }
    }

    public MyLinkedHashTable(int bucketSize) {
        this.buckets = new List[bucketSize];
        this.bucketSize = bucketSize;
        this.size = 0;

        for (int i = 0; i < bucketSize; i++) {
            this.buckets[i] = new LinkedList<>();

        }
    }

    @Override // 충돌 시, 체이닝 방법으로 해결
    public void put(K key, V value) {
        int idx = this.hash(key);
        List<Node> bucket = this.buckets[idx];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                node.data = value;
                return; // 메소드 종료
            }
        }
        Node node = new Node(key, value);
        bucket.add(node);
        this.size++;
    }

    @Override
    public V get(K key) {
        int idx = this.hash(key);
        List<Node> bucket = this.buckets[idx];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node.data; // 버킷에 데이터가 늘어날 수록, 시간 오래 걸
            }
        }
        return null;
    }
    @Override
    public boolean delete(K key) {
        int idx = this.hash(key);
        List<Node> bucket = this.buckets[idx];
        for (Iterator<Node> iter = bucket.iterator(); iter.hasNext();) {
            // hasNext()는 데이터가 남아있지 않을 때까지 실행
            Node node = iter.next();
            if (node.key.equals(key)) {
                iter.remove();
                this.size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(K key) {
        int idx = this.hash(key);
        List<Node> bucket = this.buckets[idx];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    private int hash(K key) {
        int hash = 0;
        for (Character ch : key.toString().toCharArray()) {
            hash += (int) ch;
        }
        return hash % this.bucketSize; // 모듈로 연산
    }

    private class Node {
        K key;
        V data;

        Node(K key, V data) {
            this.key = key;
            this.data = data;
        }
    }
}
