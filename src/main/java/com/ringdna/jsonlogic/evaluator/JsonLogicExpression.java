package com.ringdna.jsonlogic.evaluator;

import com.ringdna.jsonlogic.ast.JsonLogicArray;

public interface JsonLogicExpression {
    String key();

    Object evaluate(JsonLogicEvaluator evaluator, JsonLogicArray arguments, Object data)
            throws JsonLogicEvaluationException;
}
