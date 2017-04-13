package org.briarheart.algorithms.graph;

import com.google.common.graph.Graph;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A version of depth-first search was investigated in the 19th century by French mathematician
 * Charles Pierre Trémaux as a strategy for solving mazes.
 *
 * @author Roman Chigvintsev
 */
public class DepthFirstPaths<T> {
    private T initialNode;
    private Map<T, Integer> nodeIndexes;
    private boolean[] marked;
    private T[] edgeTo;

    public DepthFirstPaths(Graph<T> graph, T initialNode) {
        checkNotNull(graph, "Graph cannot be null");
        checkNotNull(initialNode, "Initial node cannot be null");

        this.initialNode = initialNode;
        nodeIndexes = new HashMap<>();
        for (T n : graph.nodes())
            if (!nodeIndexes.containsKey(n))
                nodeIndexes.put(n, nodeIndexes.size());
        int nodesSize = graph.nodes().size();
        marked = new boolean[nodesSize];
        //noinspection unchecked
        edgeTo = (T[]) new Object[nodesSize];
        findPaths(graph, initialNode);
    }

    public boolean hasPathTo(T node) {
        checkNode(node);
        Integer index = nodeIndexes.get(node);
        return marked[index];
    }

    public Iterable<T> pathTo(T node) {
        if (!hasPathTo(node))
            return Collections.emptyList();
        Deque<T> path = new LinkedList<>();
        for (T n = node; !n.equals(initialNode); n = edgeTo[nodeIndexes.get(n)])
            path.push(n);
        path.push(initialNode);
        return path;
    }

    private void findPaths(Graph<T> graph, T node) {
        marked[nodeIndexes.get(node)] = true;
        for (T n : graph.adjacentNodes(node)) {
            int nIndex = nodeIndexes.get(n);
            if (!marked[nIndex]) {
                edgeTo[nIndex] = node;
                findPaths(graph, n);
            }
        }
    }

    private void checkNode(T node) {
        checkNotNull(node, "Node cannot be null");
        Integer index = nodeIndexes.get(node);
        checkArgument(index != null && index < marked.length, "Given node does not belong to this graph");
    }
}
