package org.briarheart.algorithms.graph;

import com.google.common.graph.Graph;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Roman Chigvintsev
 */
public abstract class AbstractGraphAlgorithm<T> {
    protected SymbolGraph<T> symbolGraph;

    public AbstractGraphAlgorithm(Graph<T> graph) {
        checkNotNull(graph, "Graph cannot be null");
        if (graph instanceof SymbolGraph)
            symbolGraph = (SymbolGraph<T>) graph;
        else
            symbolGraph = new SymbolGraph<>(graph);
    }
}
