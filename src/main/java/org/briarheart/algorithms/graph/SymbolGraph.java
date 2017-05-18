package org.briarheart.algorithms.graph;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Roman Chigvintsev
 */
public class SymbolGraph<T> implements Graph<T> {
    private Graph<T> delegate;
    private Map<T, Integer> nodeIndexes = new HashMap<>();

    public SymbolGraph(Graph<T> delegate) {
        checkNotNull(delegate, "Delegate cannot be null");
        for (T node : delegate.nodes())
            if (!nodeIndexes.containsKey(node))
                nodeIndexes.put(node, nodeIndexes.size());
        this.delegate = delegate;
    }

    public Integer indexOf(T node) {
        Integer index = nodeIndexes.get(node);
        checkArgument(index != null, "Given node is null or does not belong to this graph");
        return index;
    }

    @Override
    public Set<T> nodes() {
        return delegate.nodes();
    }

    @Override
    public Set<EndpointPair<T>> edges() {
        return delegate.edges();
    }

    @Override
    public boolean isDirected() {
        return delegate.isDirected();
    }

    @Override
    public boolean allowsSelfLoops() {
        return delegate.allowsSelfLoops();
    }

    @Override
    public ElementOrder<T> nodeOrder() {
        return delegate.nodeOrder();
    }

    @Override
    public Set<T> adjacentNodes(Object node) {
        return delegate.adjacentNodes(node);
    }

    @Override
    public Set<T> predecessors(Object node) {
        return delegate.predecessors(node);
    }

    @Override
    public Set<T> successors(Object node) {
        return delegate.successors(node);
    }

    @Override
    public int degree(Object node) {
        return delegate.degree(node);
    }

    @Override
    public int inDegree(Object node) {
        return delegate.inDegree(node);
    }

    @Override
    public int outDegree(Object node) {
        return delegate.outDegree(node);
    }
}
