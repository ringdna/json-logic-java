package com.ringdna.jsonlogic.evaluator.expressions;

import com.ringdna.jsonlogic.JsonLogic;
import com.ringdna.jsonlogic.ast.JsonLogicArray;
import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluationException;
import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluator;
import com.ringdna.jsonlogic.evaluator.JsonLogicExpression;
import com.ringdna.jsonlogic.utils.ArrayLike;

public class AllExpression implements JsonLogicExpression {
    public static final AllExpression INSTANCE = new AllExpression();

    private AllExpression() {
        // Use INSTANCE instead.
    }

    @Override
    public String key() {
        return "all";
    }

    @Override
    public Object evaluate(JsonLogicEvaluator evaluator, JsonLogicArray arguments, Object data)
            throws JsonLogicEvaluationException {
        if (arguments.size() != 2) {
            throw new JsonLogicEvaluationException("all expects exactly 2 arguments");
        }

        Object maybeArray = evaluator.evaluate(arguments.get(0), data);

        if (!ArrayLike.isEligible(maybeArray)) {
            throw new JsonLogicEvaluationException("first argument to all must be a valid array");
        }

        ArrayLike array = new ArrayLike(maybeArray);

        if (array.size() < 1) {
            return false;
        }

        for (Object item : array) {
            if (!JsonLogic.truthy(evaluator.evaluate(arguments.get(1), item))) {
                return false;
            }
        }

        return true;
    }
}
