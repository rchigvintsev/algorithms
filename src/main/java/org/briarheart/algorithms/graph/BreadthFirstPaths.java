package org.briarheart.algorithms.graph;

import com.google.common.graph.Graph;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Roman Chigvintsev
 */
public class BreadthFirstPaths<T> extends AbstractGraphAlgorithm<T> {
    private boolean[] marked;
    private T[] edgeTo;
    private int[] distanceTo;

    public BreadthFirstPaths(Graph<T> graph, T initialNode) {
        super(graph);
        checkNotNull(initialNode, "Initial node cannot be null");
        int nodesSize = symbolGraph.nodes().size();
        marked = new boolean[nodesSize];
        //noinspection unchecked
        edgeTo = (T[]) new Object[nodesSize];
        distanceTo = new int[nodesSize];
        Arrays.fill(distanceTo, Integer.MAX_VALUE);
        findPaths(initialNode);
    }

    public boolean hasPathTo(T node) {
        return marked[symbolGraph.indexOf(node)];
    }

    public Iterable<T> pathTo(T node) {
        if (!hasPathTo(node))
            return Collections.emptyList();
        Deque<T> path = new LinkedList<>();
        while (distanceTo[symbolGraph.indexOf(node)] != 0) {
            path.push(node);
            node = edgeTo[symbolGraph.indexOf(node)];
        }
        path.push(node);
        return path;
    }

    public int distanceTo(T node) {
        return distanceTo[symbolGraph.indexOf(node)];
    }

    private void findPaths(T initialNode) {
        Queue<T> queue = new LinkedList<>();
        marked[symbolGraph.indexOf(initialNode)] = true;
        distanceTo[symbolGraph.indexOf(initialNode)] = 0;
        queue.offer(initialNode);

        while (!queue.isEmpty()) {
            T node = queue.poll();
            for (T n : symbolGraph.successors(node)) {
                int nIndex = symbolGraph.indexOf(n);
                if (!marked[nIndex]) {
                    edgeTo[nIndex] = node;
                    distanceTo[nIndex] = distanceTo[symbolGraph.indexOf(node)] + 1;
                    marked[nIndex] = true;
                    queue.offer(n);
                }
            }
        }
    }
}
