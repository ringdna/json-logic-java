package com.ringdna.jsonlogic.evaluator.expressions;

import com.ringdna.jsonlogic.ast.JsonLogicArray;
import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluationException;
import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluator;
import com.ringdna.jsonlogic.evaluator.JsonLogicExpression;

public class StrictInequalityExpression implements JsonLogicExpression {
    public static final StrictInequalityExpression INSTANCE =
            new StrictInequalityExpression(StrictEqualityExpression.INSTANCE);

    private final StrictEqualityExpression delegate;

    private StrictInequalityExpression(StrictEqualityExpression delegate) {
        this.delegate = delegate;
    }

    @Override
    public String key() {
        return "!==";
    }

    @Override
    public Object evaluate(JsonLogicEvaluator evaluator, JsonLogicArray arguments, Object data)
            throws JsonLogicEvaluationException {
        boolean result = (boolean) delegate.evaluate(evaluator, arguments, data);

        return !result;
    }
}
