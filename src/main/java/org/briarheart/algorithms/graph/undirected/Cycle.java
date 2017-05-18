package org.briarheart.algorithms.graph.undirected;

import com.google.common.graph.Graph;
import org.briarheart.algorithms.graph.SymbolGraph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Roman Chigvintsev
 */
public class Cycle<T> {
    private SymbolGraph<T> symbolGraph;
    private boolean[] marked;
    private T[] edgeTo;
    private Deque<T> cycle;

    @SuppressWarnings("unchecked")
    public Cycle(Graph<T> graph) {
        symbolGraph = new SymbolGraph<>(graph);
        cycle = findSelfLoop();
        if (cycle == null) {
            cycle = findParallelEdges();
            if (cycle == null) {
                int nodesSize = graph.nodes().size();
                marked = new boolean[nodesSize];
                edgeTo = (T[]) new Object[nodesSize];
                for (T node : symbolGraph.nodes())
                    if (!marked[symbolGraph.indexOf(node)])
                        depthFirstSearch(null, node);
            }
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<T> getCycle() {
        return cycle;
    }

    private void depthFirstSearch(T prevNode, T node) {
        marked[symbolGraph.indexOf(node)] = true;
        for (T adjacentNode : symbolGraph.adjacentNodes(node)) {
            if (cycle != null)
                return;
            int nIndex = symbolGraph.indexOf(adjacentNode);
            if (!marked[nIndex]) {
                edgeTo[nIndex] = node;
                depthFirstSearch(node, adjacentNode);
            } else if (!adjacentNode.equals(prevNode)) {
                cycle = new LinkedList<>();
                for (T n = node; !n.equals(adjacentNode); n = edgeTo[symbolGraph.indexOf(n)])
                    cycle.push(n);
                cycle.push(adjacentNode);
                cycle.push(node);
            }
        }
    }

    private Deque<T> findSelfLoop() {
        for (T node : symbolGraph.nodes())
            for (T adjacentNode : symbolGraph.adjacentNodes(node))
                if (node.equals(adjacentNode)) {
                    Deque<T> cycle = new LinkedList<>();
                    cycle.push(node);
                    cycle.push(adjacentNode);
                    return cycle;
                }
        return null;
    }

    private Deque<T> findParallelEdges() {
        boolean[] marked = new boolean[symbolGraph.nodes().size()];

        for (T node : symbolGraph.nodes()) {
            for (T adjacentNode : symbolGraph.adjacentNodes(node)) {
                int nIndex = symbolGraph.indexOf(adjacentNode);
                if (marked[nIndex]) {
                    Deque<T> cycle = new LinkedList<>();
                    cycle.push(node);
                    cycle.push(adjacentNode);
                    cycle.push(node);
                    return cycle;
                }
                marked[nIndex] = true;
            }

            for (T adjacentNode : symbolGraph.adjacentNodes(node))
                marked[symbolGraph.indexOf(adjacentNode)] = false;
        }
        return null;
    }
}
