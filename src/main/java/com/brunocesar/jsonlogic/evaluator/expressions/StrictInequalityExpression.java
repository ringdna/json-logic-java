package com.brunocesar.jsonlogic.evaluator.expressions;

import com.brunocesar.jsonlogic.ast.JsonLogicArray;
import com.brunocesar.jsonlogic.evaluator.JsonLogicEvaluationException;
import com.brunocesar.jsonlogic.evaluator.JsonLogicEvaluator;
import com.brunocesar.jsonlogic.evaluator.JsonLogicExpression;

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
