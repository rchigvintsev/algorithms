package org.briarheart.algorithms.graph;

import com.google.common.graph.Graph;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A version of depth-first search was investigated in the 19th century by French mathematician
 * Charles Pierre Trémaux as a strategy for solving mazes.
 *
 * @author Roman Chigvintsev
 */
public class DepthFirstPaths<T> extends AbstractGraphAlgorithm<T> {
    private T initialNode;
    private boolean[] marked;
    private T[] edgeTo;

    @SuppressWarnings("unchecked")
    public DepthFirstPaths(Graph<T> graph, T initialNode) {
        super(graph);
        checkNotNull(initialNode, "Initial node cannot be null");
        this.initialNode = initialNode;
        int nodesSize = symbolGraph.nodes().size();
        marked = new boolean[nodesSize];
        edgeTo = (T[]) new Object[nodesSize];
        findPaths(initialNode);
    }

    public boolean hasPathTo(T node) {
        return marked[symbolGraph.indexOf(node)];
    }

    public Iterable<T> pathTo(T node) {
        if (!hasPathTo(node))
            return Collections.emptyList();
        Deque<T> path = new LinkedList<>();
        for (T n = node; !n.equals(initialNode); n = edgeTo[symbolGraph.indexOf(n)])
            path.push(n);
        path.push(initialNode);
        return path;
    }

    private void findPaths(T node) {
        marked[symbolGraph.indexOf(node)] = true;
        for (T adjacentNode : symbolGraph.successors(node)) {
            int nIndex = symbolGraph.indexOf(adjacentNode);
            if (!marked[nIndex]) {
                edgeTo[nIndex] = node;
                findPaths(adjacentNode);
            }
        }
    }
}
