package com.brunocesar.jsonlogic.evaluator.expressions;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import com.brunocesar.jsonlogic.evaluator.JsonLogicEvaluationException;

import java.util.List;

public class ConcatenateExpression extends PreEvaluatedArgumentsExpression {
  public static final ConcatenateExpression INSTANCE = new ConcatenateExpression();

  private ConcatenateExpression() {
    // Use INSTANCE instead.
  }

  @Override
  public String key() {
    return "cat";
  }

  @Override
  public Object evaluate(List arguments, Object data) throws JsonLogicEvaluationException {
    return FluentIterable.from(arguments)
            .transform(new Function<Object, Object>() {
              @Override
              public Object apply(Object obj) {
                if (obj instanceof Double && obj.toString().endsWith(".0")) {
                  return ((Double) obj).intValue();
                }

                return obj;
              }
            })
            .transform(Functions.toStringFunction())
            .join(Joiner.on(""));
  }
}
