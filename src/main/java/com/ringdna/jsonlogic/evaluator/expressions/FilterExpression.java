package com.ringdna.jsonlogic.evaluator.expressions;

import com.ringdna.jsonlogic.JsonLogic;
import com.ringdna.jsonlogic.ast.JsonLogicArray;
import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluationException;
import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluator;
import com.ringdna.jsonlogic.evaluator.JsonLogicExpression;
import com.ringdna.jsonlogic.utils.ArrayLike;

import java.util.ArrayList;
import java.util.List;

public class FilterExpression implements JsonLogicExpression {
    public static final FilterExpression INSTANCE = new FilterExpression();

    private FilterExpression() {
        // Use INSTANCE instead.
    }

    @Override
    public String key() {
        return "filter";
    }

    @Override
    public Object evaluate(JsonLogicEvaluator evaluator, JsonLogicArray arguments, Object data)
            throws JsonLogicEvaluationException {
        if (arguments.size() != 2) {
            throw new JsonLogicEvaluationException("filter expects exactly 2 arguments");
        }

        Object maybeArray = evaluator.evaluate(arguments.get(0), data);

        if (!ArrayLike.isEligible(maybeArray)) {
            throw new JsonLogicEvaluationException("first argument to filter must be a valid array");
        }

        List<Object> result = new ArrayList<>();

        for (Object item : new ArrayLike(maybeArray)) {
            if (JsonLogic.truthy(evaluator.evaluate(arguments.get(1), item))) {
                result.add(item);
            }
        }

        return result;
    }
}
