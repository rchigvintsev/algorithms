package org.briarheart.algorithms.graph;

import com.google.common.graph.Graph;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Roman Chigvintsev
 */
public class BreadthFirstPaths<T> {
    private Map<T, Integer> nodeIndexes;
    private boolean[] marked;
    private T[] edgeTo;
    private int[] distanceTo;

    public BreadthFirstPaths(Graph<T> graph, T node) {
       this(graph, Collections.singletonList(node));
    }

    public BreadthFirstPaths(Graph<T> graph, Iterable<T> nodes) {
        checkNotNull(graph, "Graph cannot be null");
        checkNotNull(nodes, "Nodes cannot be null");

        nodeIndexes = new HashMap<>();
        for (T n : graph.nodes())
            if (!nodeIndexes.containsKey(n))
                nodeIndexes.put(n, nodeIndexes.size());

        int nodesSize = graph.nodes().size();
        marked = new boolean[nodesSize];
        //noinspection unchecked
        edgeTo = (T[]) new Object[nodesSize];
        distanceTo = new int[nodesSize];
        Arrays.fill(distanceTo, Integer.MAX_VALUE);
        findPaths(graph, nodes);
    }

    public boolean hasPathTo(T node) {
        checkNode(node);
        return marked[nodeIndexes.get(node)];
    }

    public Iterable<T> pathTo(T node) {
        if (!hasPathTo(node))
            return Collections.emptyList();
        Deque<T> path = new LinkedList<>();
        while (distanceTo[nodeIndexes.get(node)] != 0) {
            path.push(node);
            node = edgeTo[nodeIndexes.get(node)];
        }
        path.push(node);
        return path;
    }

    public int distanceTo(T node) {
        checkNode(node);
        return distanceTo[nodeIndexes.get(node)];
    }

    private void findPaths(Graph<T> graph, Iterable<T> nodes) {
        Queue<T> queue = new LinkedList<>();
        for (T n : nodes) {
            int nIndex = nodeIndexes.get(n);
            marked[nIndex] = true;
            distanceTo[nIndex] = 0;
            queue.offer(n);
        }

        while (!queue.isEmpty()) {
            T node = queue.poll();
            for (T n : graph.adjacentNodes(node)) {
                int nIndex = nodeIndexes.get(n);
                if (!marked[nIndex]) {
                    edgeTo[nIndex] = node;
                    distanceTo[nIndex] = distanceTo[nodeIndexes.get(node)] + 1;
                    marked[nIndex] = true;
                    queue.offer(n);
                }
            }
        }
    }

    private void checkNode(T node) {
        checkNotNull(node, "Node cannot be null");
        Integer index = nodeIndexes.get(node);
        checkArgument(index != null && index < marked.length, "Given node does not belong to this graph");
    }
}
