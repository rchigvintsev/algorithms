package org.briarheart.algorithm.graph;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;

import java.util.Collections;
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
    public ElementOrder<T> incidentEdgeOrder() {
        return null;
    }

    @Override
    public Set<EndpointPair<T>> incidentEdges(T node) {
        return null;
    }

    @Override
    public boolean hasEdgeConnecting(T nodeU, T nodeV) {
        return false;
    }

    @Override
    public boolean hasEdgeConnecting(EndpointPair<T> endpoints) {
        return false;
    }

    @Override
    public Set<T> adjacentNodes(Object node) {
        return Collections.emptySet();
    }

    @Override
    public Set<T> predecessors(Object node) {
        return Collections.emptySet();
    }

    @Override
    public Set<T> successors(Object node) {
        return Collections.emptySet();
    }

    @Override
    public int degree(Object node) {
        return 0;
    }

    @Override
    public int inDegree(Object node) {
        return 0;
    }

    @Override
    public int outDegree(Object node) {
        return 0;
    }
}
