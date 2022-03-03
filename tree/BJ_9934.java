package tree;

import javax.xml.soap.Node;
import java.util.*;

public class BJ_9934 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 트리의 노드 갯수
        int k = (int) (Math.pow(2, sc.nextInt()) - 1);
        List<Integer> l = new ArrayList<>();

        // 인오더 탐색의 결과로 방문한 노드
        for (int i = 0; i < k; i++) {
            l.add(sc.nextInt());
        }

        Node root = BJ_9934.buildTree(l, 0, l.size() - 1);
        printTree(root);

    }

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) { this.data = data; }
    }

    static Node buildTree(List<Integer> inorder, int start, int end) {
        if (start > end) {
            return null;
        }

        int i = (start + end) / 2; // 중간 값
        Node node = new Node(inorder.get(i)); // root node

        if (start == end) { // leaf
            return node;
        }

        node.left = buildTree(inorder, start, i - 1);
        node.right = buildTree(inorder, i + 1, end);
        return node;
    }

    static void printTree(Node root) {
        StringBuilder result = new StringBuilder();

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                Node node = queue.poll();
                result.append(node.data + " ");
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.append("\n");
        }

        System.out.println(result.toString());
    }
}
