package org.briarheart.algorithms.graph.undirected;

import com.google.common.graph.Graph;
import org.briarheart.algorithms.graph.AbstractGraphAlgorithm;

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
        int nodesSize = graph.nodes().size();
        marked = new boolean[nodesSize];
        //noinspection unchecked
        edgeTo = (T[]) new Object[nodesSize];
        distanceTo = new int[nodesSize];
        Arrays.fill(distanceTo, Integer.MAX_VALUE);
        findPaths(graph, initialNode);
    }

    public boolean hasPathTo(T node) {
        checkNode(node);
        return marked[indexOf(node)];
    }

    public Iterable<T> pathTo(T node) {
        if (!hasPathTo(node))
            return Collections.emptyList();
        Deque<T> path = new LinkedList<>();
        while (distanceTo[indexOf(node)] != 0) {
            path.push(node);
            node = edgeTo[indexOf(node)];
        }
        path.push(node);
        return path;
    }

    public int distanceTo(T node) {
        checkNode(node);
        return distanceTo[indexOf(node)];
    }

    private void findPaths(Graph<T> graph, T initialNode) {
        Queue<T> queue = new LinkedList<>();
        marked[indexOf(initialNode)] = true;
        distanceTo[indexOf(initialNode)] = 0;
        queue.offer(initialNode);

        while (!queue.isEmpty()) {
            T node = queue.poll();
            for (T n : graph.adjacentNodes(node)) {
                int nIndex = indexOf(n);
                if (!marked[nIndex]) {
                    edgeTo[nIndex] = node;
                    distanceTo[nIndex] = distanceTo[indexOf(node)] + 1;
                    marked[nIndex] = true;
                    queue.offer(n);
                }
            }
        }
    }
}
