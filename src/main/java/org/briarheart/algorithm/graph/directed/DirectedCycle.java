package org.briarheart.algorithm.graph.directed;

import com.google.common.graph.Graph;
import org.briarheart.algorithm.graph.AbstractGraphAlgorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Roman Chigvintsev
 */
public class DirectedCycle<T> extends AbstractGraphAlgorithm<T> {
    private boolean[] marked;
    private T[] edgeTo;
    private boolean[] onStack;
    private Deque<T> cycle;

    @SuppressWarnings("unchecked")
    public DirectedCycle(Graph<T> graph) {
        super(graph);
        int nodesSize = symbolGraph.nodes().size();
        marked = new boolean[nodesSize];
        onStack = new boolean[nodesSize];
        edgeTo = (T[]) new Object[nodesSize];
        for (T node : symbolGraph.nodes())
            if (!marked[symbolGraph.indexOf(node)] && cycle == null)
                depthFirstSearch(node);
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<T> getCycle() {
        return cycle;
    }

    private void depthFirstSearch(T node) {
        onStack[symbolGraph.indexOf(node)] = true;
        marked[symbolGraph.indexOf(node)] = true;
        for (T adjacentNode : symbolGraph.successors(node)) {
            if (cycle != null)
                return;
            if (!marked[symbolGraph.indexOf(adjacentNode)]) {
                edgeTo[symbolGraph.indexOf(adjacentNode)] = node;
                depthFirstSearch(adjacentNode);
            } else if (onStack[symbolGraph.indexOf(adjacentNode)]) {
                cycle = new LinkedList<>();
                for (T n = node; !n.equals(adjacentNode); n = edgeTo[symbolGraph.indexOf(n)])
                    cycle.push(n);
                cycle.push(adjacentNode);
                cycle.push(node);
            }
        }
        onStack[symbolGraph.indexOf(node)] = false;
    }
}
