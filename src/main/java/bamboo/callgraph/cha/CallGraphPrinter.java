/*
 * Bamboo - A Program Analysis Framework for Java
 *
 * Copyright (C)  2020 Tian Tan <tiantan@nju.edu.cn>
 * Copyright (C)  2020 Yue Li <yueli@nju.edu.cn>
 * All rights reserved.
 *
 * This software is designed for the "Static Program Analysis" course at
 * Nanjing University, and it supports a subset of Java features.
 * Bamboo is only for educational and academic purposes, and any form of
 * commercial use is disallowed.
 */

package bamboo.callgraph.cha;

import bamboo.callgraph.CallGraph;
import bamboo.util.SootUtils;
import soot.Body;
import soot.BodyTransformer;
import soot.BriefUnitPrinter;
import soot.SootMethod;
import soot.Unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Print call edges of each invocation Unit.
 */
public class CallGraphPrinter extends BodyTransformer {

    private static final CallGraphPrinter INSTANCE = new CallGraphPrinter();

    public static CallGraphPrinter v() {
        return INSTANCE;
    }

    private static boolean isOutput = true;

    public static void setOutput(boolean isOutput) {
        CallGraphPrinter.isOutput = isOutput;
    }

    private CallGraphPrinter() {};

    @Override
    protected synchronized void internalTransform(Body b, String phaseName, Map<String, String> options) {
        CallGraph<Unit, SootMethod> callGraph = CHACallGraphBuilder.v()
                .getRecentCallGraph();
        boolean hasCallees = false;
        BriefUnitPrinter up = new BriefUnitPrinter(b);
        for (Unit u : b.getUnits()) {
            Collection<SootMethod> callees = callGraph.getCallees(u);
            if (!callees.isEmpty()) {
                if (!hasCallees) {
                    hasCallees = true;
                    System.out.println("------ " + b.getMethod() + " [call graph] -----");
                }
                System.out.print(SootUtils.unitToString(up, u) + " -> ");
                System.out.println(new ArrayList<>(callees));
            }
        }
        if (hasCallees) {
            System.out.println();
        }
    }
}
