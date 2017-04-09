package org.briarheart.algorithms.graph;

import com.google.common.graph.Graph;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Roman Chigvintsev
 */
public class ConnectedComponents<T> {
    private Map<T, Integer> nodeIndexes;
    private boolean[] marked;
    private int[] id;
    private int[] size;
    private int count;

    public ConnectedComponents(Graph<T> graph) {
        checkNotNull(graph, "Graph cannot be null");

        nodeIndexes = new HashMap<>();
        for (T n : graph.nodes())
            if (!nodeIndexes.containsKey(n))
                nodeIndexes.put(n, nodeIndexes.size());

        int nodesSize = graph.nodes().size();
        marked = new boolean[nodesSize];
        id = new int[nodesSize];
        size = new int[nodesSize];

        for (T node : graph.nodes())
            if (!marked[nodeIndexes.get(node)]) {
                depthFirstSearch(graph, node);
                count++;
            }
    }

    public int componentId(T node) {
        checkNode(node);
        return id[nodeIndexes.get(node)];
    }

    public int componentSize(T node) {
        checkNode(node);
        return size[id[nodeIndexes.get(node)]];
    }

    public int count() {
        return count;
    }

    public boolean connected(T n1, T n2) {
        return componentId(n1) == componentId(n2);
    }

    private void depthFirstSearch(Graph<T> graph, T node) {
        int nIndex = nodeIndexes.get(node);
        marked[nIndex] = true;
        id[nIndex] = count;
        size[count]++;
        for (T n : graph.adjacentNodes(node))
            if (!marked[nodeIndexes.get(n)])
                depthFirstSearch(graph, n);
    }

    private void checkNode(T node) {
        checkNotNull(node, "Node cannot be null");
        Integer index = nodeIndexes.get(node);
        checkArgument(index != null && index < marked.length, "Given node does not belong to this graph");
    }
}
