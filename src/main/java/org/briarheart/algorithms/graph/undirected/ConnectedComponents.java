package org.briarheart.algorithms.graph.undirected;

import com.google.common.graph.Graph;
import org.briarheart.algorithms.graph.AbstractGraphAlgorithm;

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
        int nodesSize = graph.nodes().size();
        marked = new boolean[nodesSize];
        id = new int[nodesSize];
        size = new int[nodesSize];

        for (T node : graph.nodes())
            if (!marked[indexOf(node)]) {
                depthFirstSearch(graph, node);
                count++;
            }
    }

    public int componentId(T node) {
        checkNode(node);
        return id[indexOf(node)];
    }

    public int componentSize(T node) {
        checkNode(node);
        return size[id[indexOf(node)]];
    }

    public int count() {
        return count;
    }

    public boolean connected(T n1, T n2) {
        return componentId(n1) == componentId(n2);
    }

    private void depthFirstSearch(Graph<T> graph, T node) {
        int nIndex = indexOf(node);
        marked[nIndex] = true;
        id[nIndex] = count;
        size[count]++;
        for (T n : graph.adjacentNodes(node))
            if (!marked[indexOf(n)])
                depthFirstSearch(graph, n);
    }
}
