package com.brunocesar.jsonlogic.evaluator;

import com.brunocesar.jsonlogic.ast.JsonLogicArray;

public interface JsonLogicExpression {
  String key();

  Object evaluate(JsonLogicEvaluator evaluator, JsonLogicArray arguments, Object data)
    throws JsonLogicEvaluationException;
}
