/*
 * Tai-e: A Static Analysis Framework for Java
 *
 * Copyright (C) 2022 Tian Tan <tiantan@nju.edu.cn>
 * Copyright (C) 2022 Yue Li <yueli@nju.edu.cn>
 *
 * This file is part of Tai-e.
 *
 * Tai-e is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * Tai-e is distributed in the hope that it will be useful,but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Tai-e. If not, see <https://www.gnu.org/licenses/>.
 */

package pascal.taie.analysis.bugfinder;

import pascal.taie.language.classes.JClass;
import pascal.taie.language.classes.JMethod;

import javax.annotation.Nonnull;
import java.util.Objects;

//TODO: refactor it with more precise context information.
public class BugInstance implements Comparable<BugInstance> {

    private final BugType type;

    private final Severity severity;

    private JClass jClass;

    private JMethod jMethod;

    private int sourceLineStart = -1, sourceLineEnd = -2;

    public BugInstance(@Nonnull BugType type, Severity severity) {
        this.type = type;
        this.severity = severity;
    }

    public Severity getSeverity() {
        return severity;
    }

    private static String getString(Object o) {
        return o == null ? "empty" : o.toString();
    }

    public static BugInstance newBugInstance(BugType type, Severity severity, JMethod method, int lineNum) {
        return new BugInstance(type, severity).setClassAndMethod(method).setSourceLine(lineNum);
    }

    @Override
    public String toString() {
        String sourcelineRange = "empty";
        if (sourceLineStart >= 0) {
            sourcelineRange = sourceLineStart == sourceLineEnd ? String.valueOf(sourceLineStart) :
                    sourceLineStart + "---" + sourceLineEnd;
        }
        return String.format("Class: %s, Method: %s, LineNumber: %s. \nbug type: %s, severity: %s",
                getString(jClass), getString(jMethod), sourcelineRange, type, severity
        );
    }

    public BugType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof BugInstance bugInstance)) return false;

        return type.equals(bugInstance.type) && Objects.equals(jClass, bugInstance.jClass)
                && Objects.equals(jMethod, bugInstance.jMethod) && sourceLineStart == bugInstance.sourceLineStart
                && sourceLineEnd == bugInstance.sourceLineEnd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, jClass, jMethod, sourceLineStart, sourceLineEnd);
    }

    @Override
    public int compareTo(BugInstance o) {
        return Integer.compare(sourceLineStart, o.sourceLineStart);
    }

    public BugInstance setClassAndMethod(JMethod method) {
        setMethod(method);
        setClass(method.getDeclaringClass());
        return this;
    }

    public BugInstance setClass(JClass clazz) {
        jClass = clazz;
        return this;
    }

    public BugInstance setMethod(JMethod method) {
        jMethod = method;
        return this;
    }

    public BugInstance setSourceLine(int num) {
        sourceLineStart = sourceLineEnd = num;
        return this;
    }

    public BugInstance setSourceLine(int start, int end) {
        sourceLineStart = start;
        sourceLineEnd = end;
        return this;
    }

    public int getSourceLineStart() {
        return sourceLineStart;
    }

    public int getSourceLineEnd() {
        return sourceLineEnd;
    }

}
