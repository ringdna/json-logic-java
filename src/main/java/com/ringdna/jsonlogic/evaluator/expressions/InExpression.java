package com.ringdna.jsonlogic.evaluator.expressions;

import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluationException;
import com.ringdna.jsonlogic.utils.ArrayLike;

import java.util.List;

public class InExpression extends PreEvaluatedArgumentsExpression {
    public static final InExpression INSTANCE = new InExpression();

    private InExpression() {
        // Use INSTANCE instead.
    }

    @Override
    public String key() {
        return "in";
    }

    @Override
    public Object evaluate(List arguments, Object data) throws JsonLogicEvaluationException {
        if (arguments.size() < 2) {
            return false;
        }

        // Handle string in (substring)
        if (arguments.get(1) instanceof String) {
            return ((String) arguments.get(1)).contains(arguments.get(0).toString());
        }

        if (ArrayLike.isEligible(arguments.get(1))) {
            return new ArrayLike(arguments.get(1)).contains(arguments.get(0));
        }

        return false;
    }
}
