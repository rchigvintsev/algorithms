package org.briarheart.algorithm.graph.undirected;

import com.google.common.graph.Graph;
import org.briarheart.algorithm.graph.AbstractGraphAlgorithm;

/**
 * @author Roman Chigvintsev
 */
public class ConnectedComponents<T> extends AbstractGraphAlgorithm<T> {
    private boolean[] marked;
    private int[] id;
    private int[] size;
    private int count;

    public ConnectedComponents(Graph<T> graph) {
        super(graph);
        int nodesSize = symbolGraph.nodes().size();
        marked = new boolean[nodesSize];
        id = new int[nodesSize];
        size = new int[nodesSize];

        for (T node : symbolGraph.nodes())
            if (!marked[symbolGraph.indexOf(node)]) {
                depthFirstSearch(node);
                count++;
            }
    }

    public int componentId(T node) {
        return id[symbolGraph.indexOf(node)];
    }

    public int componentSize(T node) {
        return size[id[symbolGraph.indexOf(node)]];
    }

    public int count() {
        return count;
    }

    public boolean connected(T n1, T n2) {
        return componentId(n1) == componentId(n2);
    }

    private void depthFirstSearch(T node) {
        int nIndex = symbolGraph.indexOf(node);
        marked[nIndex] = true;
        id[nIndex] = count;
        size[count]++;
        for (T n : symbolGraph.adjacentNodes(node))
            if (!marked[symbolGraph.indexOf(n)])
                depthFirstSearch(n);
    }
}
