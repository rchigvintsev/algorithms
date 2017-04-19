package org.briarheart.algorithms.graph.undirected;

import com.google.common.graph.Graph;
import org.briarheart.algorithms.graph.AbstractGraphAlgorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Roman Chigvintsev
 */
public class Cycle<T> extends AbstractGraphAlgorithm<T> {
    private boolean[] marked;
    private T[] edgeTo;
    private Deque<T> cycle;

    @SuppressWarnings("unchecked")
    public Cycle(Graph<T> graph) {
        super(graph);
        cycle = findSelfLoop(graph);
        if (cycle == null) {
            cycle = findParallelEdges(graph);
            if (cycle == null) {
                int nodesSize = graph.nodes().size();
                marked = new boolean[nodesSize];
                edgeTo = (T[]) new Object[nodesSize];
                for (T node : graph.nodes())
                    if (!marked[indexOf(node)])
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
        marked[indexOf(node)] = true;
        for (T adjacentNode : graph.adjacentNodes(node)) {
            if (cycle != null)
                return;
            int nIndex = indexOf(adjacentNode);
            if (!marked[nIndex]) {
                edgeTo[nIndex] = node;
                depthFirstSearch(graph, node, adjacentNode);
            } else if (!adjacentNode.equals(prevNode)) {
                cycle = new LinkedList<>();
                for (T n = node; !n.equals(adjacentNode); n = edgeTo[indexOf(n)])
                    cycle.push(n);
                cycle.push(adjacentNode);
                cycle.push(node);
            }
        }
    }

    private Deque<T> findSelfLoop(Graph<T> graph) {
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

    private Deque<T> findParallelEdges(Graph<T> graph) {
        boolean[] marked = new boolean[graph.nodes().size()];

        for (T node : graph.nodes()) {
            for (T adjacentNode : graph.adjacentNodes(node)) {
                int nIndex = indexOf(adjacentNode);
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
                marked[indexOf(adjacentNode)] = false;
        }
        return null;
    }
}
