/*
 * Bamboo - A Program Analysis Framework for Java
 *
 * Copyright (C) 2020 Tian Tan <tiantan@nju.edu.cn>
 * Copyright (C) 2020 Yue Li <yueli@nju.edu.cn>
 * All rights reserved.
 *
 * This software is designed for the "Static Program Analysis" course at
 * Nanjing University, and it supports a subset of Java features.
 * Bamboo is only for educational and academic purposes, and any form of
 * commercial use is disallowed.
 */

package bamboo.pta.env;

import bamboo.pta.analysis.ProgramManager;
import bamboo.pta.element.Type;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the string constants in the program.
 * The creation of string constants is actually controlled by runtime
 * environment, so we put them in this package.
 */
class StringConstantPool {

    private final Type stringType;

    final Map<String, StringConstant> constants = new HashMap<>();

    StringConstantPool(ProgramManager programManager) {
        this.stringType = programManager
                .getUniqueTypeByName("java.lang.String");
    }

    StringConstant getStringConstant(String constant) {
        return constants.computeIfAbsent(constant,
                (c) -> new StringConstant(stringType, c));
    }
}