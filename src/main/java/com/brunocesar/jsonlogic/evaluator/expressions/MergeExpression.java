package com.brunocesar.jsonlogic.evaluator.expressions;

import com.brunocesar.jsonlogic.evaluator.JsonLogicEvaluationException;
import com.brunocesar.jsonlogic.utils.ArrayLike;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import java.util.Collections;
import java.util.List;

public class MergeExpression extends PreEvaluatedArgumentsExpression {
  public static final MergeExpression INSTANCE = new MergeExpression();

  private MergeExpression() {
    // Use INSTANCE instead.
  }

  @Override
  public String key() {
    return "merge";
  }

  @Override
  public Object evaluate(List arguments, Object data) throws JsonLogicEvaluationException {
    return FluentIterable.from((List<Object>) arguments)
            .transformAndConcat(new Function<Object, List<Object>>() {
              @Override
              public List<Object> apply(Object obj) {
                return ArrayLike.isEligible(obj) ? new ArrayLike(obj) : (List<Object>) Collections.singleton(obj);
              }
            }).toList();
  }
}
