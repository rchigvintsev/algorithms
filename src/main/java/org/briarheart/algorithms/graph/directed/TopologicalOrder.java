package org.briarheart.algorithms.graph.directed;

import com.google.common.graph.Graph;
import org.briarheart.algorithms.graph.AbstractGraphAlgorithm;

/**
 * @author Roman Chigvintsev
 */
public class TopologicalOrder<T> extends AbstractGraphAlgorithm<T> {
    private Iterable<T> order;
    private int[] rank;

    public TopologicalOrder(Graph<T> graph) {
        super(graph);
        DirectedCycle<T> cycleFinder = new DirectedCycle<>(symbolGraph);
        if (cycleFinder.hasCycle())
            throw new IllegalArgumentException("Graph has a cycle");
        DepthFirstOrder<T> dfo = new DepthFirstOrder<>(symbolGraph);
        order = dfo.getOrder();
        rank = new int[symbolGraph.nodes().size()];
        int i = 0;
        for (T node : order)
            rank[symbolGraph.indexOf(node)] = i++;
    }

    public boolean hasOrder() {
        return order != null;
    }

    public Iterable<T> getOrder() {
        return order;
    }

    public int rankOf(T node) {
        return hasOrder() ? rank[symbolGraph.indexOf(node)] : -1;
    }
}
