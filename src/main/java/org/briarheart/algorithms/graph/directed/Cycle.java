package org.briarheart.algorithms.graph.directed;

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
    private boolean[] onStack;
    private Deque<T> cycle;

    @SuppressWarnings("unchecked")
    public Cycle(Graph<T> graph) {
        super(graph);
        int nodesSize = graph.nodes().size();
        marked = new boolean[nodesSize];
        onStack = new boolean[nodesSize];
        edgeTo = (T[]) new Object[nodesSize];
        for (T node : graph.nodes()) {
            if (!marked[indexOf(node)] && cycle == null)
                depthFirstSearch(graph, node);
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    private void depthFirstSearch(Graph<T> graph, T node) {
        onStack[indexOf(node)] = true;
        marked[indexOf(node)] = true;
        for (T adjacentNode : graph.successors(node)) {
            if (cycle != null)
                return;
            if (!marked[indexOf(adjacentNode)]) {
                edgeTo[indexOf(adjacentNode)] = node;
                depthFirstSearch(graph, adjacentNode);
            } else if (onStack[indexOf(adjacentNode)]) {
                cycle = new LinkedList<>();
                for (T n = node; !n.equals(adjacentNode); n = edgeTo[indexOf(n)])
                    cycle.push(n);
                cycle.push(adjacentNode);
                cycle.push(node);
            }
        }
        onStack[indexOf(node)] = false;
    }

    public Iterable<T> getCycle() {
        return cycle;
    }
}
