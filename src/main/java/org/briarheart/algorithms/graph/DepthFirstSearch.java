package org.briarheart.algorithms.graph;

import com.google.common.graph.Graph;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A version of depth-first search was investigated in the 19th century by French mathematician
 * Charles Pierre Trémaux as a strategy for solving mazes.
 *
 * @author Roman Chigvintsev
 */
public class DepthFirstSearch<T> {
    private Map<T, Integer> nodeIndexes;
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph<T> graph, T node) {
        checkNotNull(graph, "Graph cannot be null");
        checkNotNull(node, "Initial node cannot be null");

        nodeIndexes = new HashMap<>();
        for (T n : graph.nodes())
            if (!nodeIndexes.containsKey(n))
                nodeIndexes.put(n, nodeIndexes.size());
        marked = new boolean[graph.nodes().size()];
        doSearch(graph, node);
    }

    public boolean isMarked(T node) {
        checkNotNull(node, "Node cannot be null");
        Integer index = nodeIndexes.get(node);
        checkArgument(index != null && index < marked.length, "Given node does not belong to this graph");
        return marked[index];
    }

    public int getCount() {
        return count;
    }

    private void doSearch(Graph<T> graph, T node) {
        marked[nodeIndexes.get(node)] = true;
        count++;
        for (T n : graph.adjacentNodes(node))
            if (!marked[nodeIndexes.get(n)])
                doSearch(graph, n);
    }
}
