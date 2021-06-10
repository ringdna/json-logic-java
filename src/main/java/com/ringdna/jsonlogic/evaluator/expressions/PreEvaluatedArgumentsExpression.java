package com.ringdna.jsonlogic.evaluator.expressions;

import com.ringdna.jsonlogic.ast.JsonLogicArray;
import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluationException;
import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluator;
import com.ringdna.jsonlogic.evaluator.JsonLogicExpression;
import com.ringdna.jsonlogic.utils.ArrayLike;

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
