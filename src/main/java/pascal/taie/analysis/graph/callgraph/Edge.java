/*
 * Tai-e: A Static Analysis Framework for Java
 *
 * Copyright (C) 2020-- Tian Tan <tiantan@nju.edu.cn>
 * Copyright (C) 2020-- Yue Li <yueli@nju.edu.cn>
 * All rights reserved.
 *
 * Tai-e is only for educational and academic purposes,
 * and any form of commercial use is disallowed.
 * Distribution of Tai-e is disallowed without the approval.
 */

package pascal.taie.analysis.graph.callgraph;

import pascal.taie.util.HashUtils;

public class Edge<CallSite, Method> {

    private final CallKind kind;

    private final CallSite callSite;

    private final Method callee;

    private final int hashCode;

    public Edge(CallKind kind, CallSite callSite, Method callee) {
        this.kind = kind;
        this.callSite = callSite;
        this.callee = callee;
        hashCode = HashUtils.hash(kind, callSite, callee);
    }

    public CallKind getKind() {
        return kind;
    }

    public CallSite getCallSite() {
        return callSite;
    }

    public Method getCallee() {
        return callee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?, ?> edge = (Edge<?, ?>) o;
        return kind == edge.kind &&
                callSite.equals(edge.callSite) &&
                callee.equals(edge.callee);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return "[" + kind + "]" +
                callSite + " -> " + callee;
    }
}