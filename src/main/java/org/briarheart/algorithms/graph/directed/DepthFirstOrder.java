package org.briarheart.algorithms.graph.directed;

import com.google.common.graph.Graph;
import org.briarheart.algorithms.graph.AbstractGraphAlgorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Roman Chigvintsev
 */
public class DepthFirstOrder<T> extends AbstractGraphAlgorithm<T> {
    private Deque<T> order;
    private boolean[] marked;

    public DepthFirstOrder(Graph<T> graph) {
        super(graph);
        order = new LinkedList<>();
        marked = new boolean[symbolGraph.nodes().size()];
        for (T node : symbolGraph.nodes())
            if (!marked[symbolGraph.indexOf(node)])
                depthFirstSearch(node);
    }

    public Iterable<T> getOrder() {
        return order;
    }

    private void depthFirstSearch(T node) {
        marked[symbolGraph.indexOf(node)] = true;
        for (T adjacentNode : symbolGraph.successors(node))
            if (!marked[symbolGraph.indexOf(adjacentNode)])
                depthFirstSearch(adjacentNode);
        order.push(node);
    }
}
