package org.briarheart.algorithms.graph.directed;

import com.google.common.graph.Graph;
import org.briarheart.algorithms.graph.AbstractGraphAlgorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Roman Chigvintsev
 */
public class TopologicalOrder<T> extends AbstractGraphAlgorithm<T> {
    private Deque<T> order;
    private boolean[] marked;
    private int[] rank;

    public TopologicalOrder(Graph<T> graph) {
        super(graph);

        DirectedCycle<T> cycleFinder = new DirectedCycle<>(graph);
        if (cycleFinder.hasCycle())
            throw new IllegalArgumentException("Graph has a cycle");

        order = new LinkedList<>();
        marked = new boolean[graph.nodes().size()];
        for (T node : graph.nodes())
            if (!marked[indexOf(node)])
                depthFirstSearch(graph, node);
        rank = new int[graph.nodes().size()];
        int i = 0;
        for (T node : order)
            rank[indexOf(node)] = i++;
    }

    public boolean hasOrder() {
        return order != null;
    }

    public Iterable<T> getOrder() {
        return order;
    }

    public int rankOf(T node) {
        checkNode(node);
        return hasOrder() ? rank[indexOf(node)] : -1;
    }

    private void depthFirstSearch(Graph<T> graph, T node) {
        marked[indexOf(node)] = true;
        for (T adjacentNode : graph.successors(node))
            if (!marked[indexOf(adjacentNode)])
                depthFirstSearch(graph, adjacentNode);
        order.push(node);
    }
}
