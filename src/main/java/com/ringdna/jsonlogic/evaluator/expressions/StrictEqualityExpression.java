package com.ringdna.jsonlogic.evaluator.expressions;

import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluationException;

import java.util.List;

public class StrictEqualityExpression extends PreEvaluatedArgumentsExpression {
    public static final StrictEqualityExpression INSTANCE = new StrictEqualityExpression();

    private StrictEqualityExpression() {
        // Only one instance can be constructed. Use StrictEqualityExpression.INSTANCE
    }

    @Override
    public String key() {
        return "===";
    }

    @Override
    public Object evaluate(List arguments, Object data) throws JsonLogicEvaluationException {
        if (arguments.size() != 2) {
            throw new JsonLogicEvaluationException("equality expressions expect exactly 2 arguments");
        }

        Object left = arguments.get(0);
        Object right = arguments.get(1);

        if (left instanceof Number && right instanceof Number) {
            return ((Number) left).doubleValue() == ((Number) right).doubleValue();
        }

        if (left == right) {
            return true;
        }

        return left != null && left.equals(right);
    }
}
