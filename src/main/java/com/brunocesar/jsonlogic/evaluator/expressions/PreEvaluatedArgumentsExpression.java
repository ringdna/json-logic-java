package com.brunocesar.jsonlogic.evaluator.expressions;

import com.brunocesar.jsonlogic.ast.JsonLogicArray;
import com.brunocesar.jsonlogic.evaluator.JsonLogicEvaluationException;
import com.brunocesar.jsonlogic.evaluator.JsonLogicEvaluator;
import com.brunocesar.jsonlogic.evaluator.JsonLogicExpression;
import com.brunocesar.jsonlogic.utils.ArrayLike;

import java.util.List;

public abstract class PreEvaluatedArgumentsExpression implements JsonLogicExpression {
  public abstract Object evaluate(List arguments, Object data) throws JsonLogicEvaluationException;

  @Override
  public Object evaluate(JsonLogicEvaluator evaluator, JsonLogicArray arguments, Object data)
    throws JsonLogicEvaluationException {
    List<Object> values = evaluator.evaluate(arguments, data);

    if (values.size() == 1 && ArrayLike.isEligible(values.get(0))) {
      values = new ArrayLike(values.get(0));
    }

    return evaluate(values, data);
  }
}
