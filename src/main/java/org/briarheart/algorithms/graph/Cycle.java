package org.briarheart.algorithms.graph;

import com.google.common.graph.Graph;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Roman Chigvintsev
 */
public class Cycle<T> {
    private Map<T, Integer> nodeIndexes;
    private boolean[] marked;
    private T[] edgeTo;
    private Deque<T> cycle;

    @SuppressWarnings("unchecked")
    public Cycle(Graph<T> graph) {
        cycle = findSelfLoop(graph);
        if (cycle == null) {
            nodeIndexes = new HashMap<>();
            for (T n : graph.nodes())
                if (!nodeIndexes.containsKey(n))
                    nodeIndexes.put(n, nodeIndexes.size());

            cycle = findParallelEdges(graph, nodeIndexes);
            if (cycle == null) {
                int nodesSize = graph.nodes().size();
                marked = new boolean[nodesSize];
                edgeTo = (T[]) new Object[nodesSize];
                for (T node : graph.nodes())
                    if (!marked[nodeIndexes.get(node)])
                        depthFirstSearch(graph, null, node);
            }
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<T> getCycle() {
        return cycle;
    }

    private void depthFirstSearch(Graph<T> graph, T prevNode, T node) {
        marked[nodeIndexes.get(node)] = true;
        for (T adjacentNode : graph.adjacentNodes(node)) {
            if (cycle != null)
                return;
            int nIndex = nodeIndexes.get(adjacentNode);
            if (!marked[nIndex]) {
                edgeTo[nIndex] = node;
                depthFirstSearch(graph, node, adjacentNode);
            } else if (!adjacentNode.equals(prevNode)) {
                cycle = new LinkedList<>();
                for (T n = node; !n.equals(adjacentNode); n = edgeTo[nodeIndexes.get(n)])
                    cycle.push(n);
                cycle.push(adjacentNode);
                cycle.push(node);
            }
        }
    }

    private static <T> Deque<T> findSelfLoop(Graph<T> graph) {
        for (T node : graph.nodes())
            for (T adjacentNode : graph.adjacentNodes(node))
                if (node.equals(adjacentNode)) {
                    Deque<T> cycle = new LinkedList<>();
                    cycle.push(node);
                    cycle.push(adjacentNode);
                    return cycle;
                }
        return null;
    }

    private static <T> Deque<T> findParallelEdges(Graph<T> graph, Map<T, Integer> nodeIndexes) {
        boolean[] marked = new boolean[graph.nodes().size()];

        for (T node : graph.nodes()) {
            for (T adjacentNode : graph.adjacentNodes(node)) {
                int nIndex = nodeIndexes.get(adjacentNode);
                if (marked[nIndex]) {
                    Deque<T> cycle = new LinkedList<>();
                    cycle.push(node);
                    cycle.push(adjacentNode);
                    cycle.push(node);
                    return cycle;
                }
                marked[nIndex] = true;
            }

            for (T adjacentNode : graph.adjacentNodes(node))
                marked[nodeIndexes.get(adjacentNode)] = false;
        }
        return null;
    }
}
