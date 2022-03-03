package graph;

import java.util.*;

public class AdjacencyListGraph implements IGraph {

    private List<List<Node>> graph;
    private Set<Integer> vertexes;
    private Map<Integer, Integer> indegress;

    public AdjacencyListGraph(int numOfVertex) {
        this.vertexes = new HashSet<>();
        this.indegress = new HashMap<>();
        this.graph = new ArrayList<>(numOfVertex);
        for (int i = 0; i < numOfVertex; i++) {
            this.graph.add(new ArrayList<>()); // 리스트 초기화
        }
    }

    @Override
    public void add(int from, int to) {
        vertexes.add(from);
        vertexes.add(to);

        int count  = indegress.getOrDefault(to, 0);
        indegress.put(to, count + 1);

        List<Node> neighbors = this.graph.get(from);
        neighbors.add(new Node(from, to));
    }

    @Override
    public void add(int from, int to, Integer distance) {
        vertexes.add(from);
        vertexes.add(to);

        int count  = indegress.getOrDefault(to, 0);
        indegress.put(to, count + 1);

        // 0 -> [1, 2, 3]
        // 1 -> [2]
        // 2 -> [0, 1]
        // 3 -> [] 라고 치면 -> 전의 버텍스는 -> 뒤의 번호를 가르키고 있다.
        List<Node> neighbors = this.graph.get(from);
        neighbors.add(new Node(from, to, distance));
    }

    @Override
    public Integer getDistance(int from, int to) {
        for (Node node : this.graph.get(from)) {
            if (node.to.equals(to)) {
                return node.weight;
            }
        }
        return null; // 연결이 없다면 null
    }

    @Override
    public Map<Integer, Integer> getIndegress() {
        return this.indegress;
    }

    @Override
    public Set<Integer> getVertexes() {
        return this.vertexes;
    }

    @Override
    public List<Integer> getNodes(int vertex) {
        List<Integer> nodes = new ArrayList<>();
        for (Node node : this.graph.get(vertex)) {
            nodes.add(node.to); // vertex가 가르키는 노드 정보 출력
        }
        return nodes;
    }

    private class Node {
        Integer from; // 출발
        Integer to; // 도착
        int weight; // 가중치

        Node(int from, int to) {
            this.from = from;
            this.to = to;
            this.weight = 1;
        }

        Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

}
