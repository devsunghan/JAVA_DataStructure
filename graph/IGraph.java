package graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IGraph {
    // 1 -> 3
    void add(int from, int to);
    void add(int from, int to, Integer distance);
    Integer getDistance(int from, int to);
    Map<Integer, Integer> getIndegress(); // 키 : 노드, 밸류 : 인 디그리(차수의 수)
    Set<Integer> getVertexes();
    List<Integer> getNodes(int vertex);
}
