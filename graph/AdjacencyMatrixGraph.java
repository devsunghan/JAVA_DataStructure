package graph;

import java.util.*;

public class AdjacencyMatrixGraph implements IGraph {

    private Integer[][] matrix; // 행렬
    private Set<Integer> vertexes; // 그래프의 벌텍스 정보
    private Map<Integer, Integer> indegress; // 차수의 수
    // 3번 노드를 가르키는 노드가 3개일 때,
    // indegress.get(3) = 5 -> 노드 3을 가르키는 노드의 개수가 5
    //

    public AdjacencyMatrixGraph(int numOfVertex) {
        this.vertexes = new HashSet<>();
        this.indegress =  new HashMap<>();
        this.matrix = new Integer[numOfVertex][];
        for (int i = 0; i < numOfVertex; i++) {
            this.matrix[i] = new Integer[numOfVertex];
        }
    }

    @Override
    public void add(int from, int to) {
        this.vertexes.add(from); // set은 중복된 데이터를 저장하지 않음
        this.vertexes.add(to); // 이미 from과 to가 있으면 추가되지 않음

        // Map.add(1, 3) 값이 하나일 경우
        // map.getordefault(1, 0) -> 3
        // map.getordefault(2, 0) -> 0 // 두번째 파라미터인 default값이 나옴
        int count = this.indegress.getOrDefault(to, 0);
        indegress.put(to, count + 1);

        matrix[from][to] = 1; // 그래프에 데이터를 저장하는 로직
        // 양방향이면 matrix[to][from] = 1 도 해줘야 함
        // 연결을 끊으려면 matrix[to][from] = null 을 해줘야 함
    }

    @Override
    public void add(int from, int to, Integer distance) {
        this.vertexes.add(from);
        this.vertexes.add(to);

        int count = this.indegress.getOrDefault(to, 0);
        // to를 키값으로 하고,
        // 이미 차수가 있으면 그 차수를 쓰고, 없으면 차수를 0으로
        indegress.put(to, count + 1);

        matrix[from][to] = distance;
    }

    @Override
    public Integer getDistance(int from, int to) {
        return this.matrix[from][to];
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
        // 버텍스가 가르키는 노드를 반
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < this.matrix[vertex].length; i++) {
            if (this.matrix[vertex][i] != null) {
                result.add(i);
            }
        }
        return result;
    }
}
