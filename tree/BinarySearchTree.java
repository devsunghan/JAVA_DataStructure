package tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> implements ITree<T> {

    private Node root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public T min() {
        return this.minNode(this.root);
    }

    private T minNode(Node node) {
        T minData = node.data;
        while (node.left != null) {
            minData = node.left.data;
            node = node.left;
        }
        return minData;
    }

    public T max() {
        return this.maxNode(this.root);
    }

    private T maxNode(Node node) {
        T maxData = node.data;
        while (node.right != null) {
            maxData = node.right.data;
            node = node.right;
        }
        return maxData;
    }

    @Override
    public void insert(T val) {
        this.root = this.insertNode(this.root, val);
        this.size++;
    }

    private Node insertNode(Node node, T val) {
        if (node == null) {
            return new Node(val);
        }
        if (val.compareTo(node.data) < 0) {
            node.left = insertNode(node.left, val);
        } else if (val.compareTo(node.data) > 0) {
            node.right = insertNode(node.right, val);
        }

        return node; // 새로운 노드 추가
    }

    @Override
    public void delete(T val) {
        this.deleteNode(this.root, val);
    }

    private Node deleteNode(Node node, T val) {
        // 대상을 못 찾을 경우
        if (node == null) return null; // 안하면 nullPointException

        if (val.compareTo(node.data) < 0) {
            node.left = deleteNode(node.left, val);
        } else if (val.compareTo(node.data) > 0) {
            node.right = deleteNode(node.right, val);
        } else {
            // val == node.data;
            this.size--;
            // bst는 삭제할 노드가 leaf면, 부모에 null 리턴, 자식 하나면 부모로 보냄
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.data = this.minNode(node.right); // 오른쪽 노드 중 최소값
            node.right = deleteNode(node.right, node.data);
        }

        return node;
    }

    @Override
    public boolean contains(T val) {
        return this.containsNode(this.root, val);
    }

    private boolean containsNode(Node node, T val) {
        if (node == null) {
            return false;
        }
        // a.compareTo(b) -> a < b == -1, (a == b) == 0, a > b == 1
        if (val.compareTo(node.data) == 0) {
            return true;
        }

        if (val.compareTo(node.data) < 0) {
            return containsNode(node.left, val); // 왼쪽 서브트리에 있나 확인
        }

        return containsNode(node.right, val);
    }

    @Override
    public int size() {
        return this.size;
    }

    public List<T> preOrder() {
        return this.preOrderTree(root, new ArrayList<>());
    }

    private List<T> preOrderTree(Node node, List<T> visited) {
        if (node == null) return visited;

        visited.add(node.data);
        preOrderTree(node.left, visited);
        preOrderTree(node.right, visited);

        return visited;
    }

    public List<T> inOrder() {
        return inOrderTree(root, new ArrayList<>());
    }

    private List<T> inOrderTree(Node node, List<T> visited) {
        if (node == null) return visited;

        inOrderTree(node.left, visited);
        visited.add(node.data);
        inOrderTree(node.right, visited);

        return visited;
    }

    public List<T> postOrder() {
        return this.postOrderTree(root, new ArrayList<>());
    }

    private List<T> postOrderTree(Node node, List<T> visited) {
        if (node == null) return visited;

        postOrderTree(node.left, visited);
        postOrderTree(node.right, visited);
        visited.add(node.data);

        return visited;
    }

    private class Node {
        T data;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
        }

        Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
