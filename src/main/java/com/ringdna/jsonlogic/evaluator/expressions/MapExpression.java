package com.ringdna.jsonlogic.evaluator.expressions;

import com.ringdna.jsonlogic.ast.JsonLogicArray;
import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluationException;
import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluator;
import com.ringdna.jsonlogic.evaluator.JsonLogicExpression;
import com.ringdna.jsonlogic.utils.ArrayLike;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapExpression implements JsonLogicExpression {
    public static final MapExpression INSTANCE = new MapExpression();

    private MapExpression() {
        // Use INSTANCE instead.
    }

    @Override
    public String key() {
        return "map";
    }

    @Override
    public Object evaluate(JsonLogicEvaluator evaluator, JsonLogicArray arguments, Object data)
            throws JsonLogicEvaluationException {
        if (arguments.size() != 2) {
            throw new JsonLogicEvaluationException("map expects exactly 2 arguments");
        }

        Object maybeArray = evaluator.evaluate(arguments.get(0), data);

        if (!ArrayLike.isEligible(maybeArray)) {
            return Collections.emptyList();
        }

        List<Object> result = new ArrayList<>();

        for (Object item : new ArrayLike(maybeArray)) {
            result.add(evaluator.evaluate(arguments.get(1), item));
        }

        return result;
    }
}
