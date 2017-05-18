package org.briarheart.algorithms.graph.directed;

import com.google.common.graph.Graph;
import com.google.common.graph.Graphs;
import org.briarheart.algorithms.graph.AbstractGraphAlgorithm;

/**
 * @author Roman Chigvintsev
 */
public class KosarajuStronglyConnectedComponents<T> extends AbstractGraphAlgorithm<T> {
    private boolean[] marked;
    private int[] ids;
    private int count;

    public KosarajuStronglyConnectedComponents(Graph<T> graph) {
        super(graph);
        int nodesSize = symbolGraph.nodes().size();
        marked = new boolean[nodesSize];
        ids = new int[nodesSize];
        DepthFirstOrder<T> dfo = new DepthFirstOrder<>(Graphs.transpose(symbolGraph));
        for (T node : dfo.getOrder())
            if (!marked[symbolGraph.indexOf(node)]) {
                depthFirstSearch(node);
                count++;
            }
    }

    public int componentId(T n) {
        return ids[symbolGraph.indexOf(n)];
    }

    public int count() {
        return count;
    }

    public boolean connected(T n1, T n2) {
        return componentId(n1) == componentId(n2);
    }

    private void depthFirstSearch(T node) {
        marked[symbolGraph.indexOf(node)] = true;
        ids[symbolGraph.indexOf(node)] = count;
        for (T adjacentNode : symbolGraph.successors(node))
            if (!marked[symbolGraph.indexOf(adjacentNode)])
                depthFirstSearch(adjacentNode);
    }
}
