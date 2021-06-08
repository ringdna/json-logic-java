package com.brunocesar.jsonlogic.evaluator.expressions;

import com.brunocesar.jsonlogic.ast.JsonLogicArray;
import com.brunocesar.jsonlogic.evaluator.JsonLogicEvaluationException;
import com.brunocesar.jsonlogic.evaluator.JsonLogicEvaluator;
import com.brunocesar.jsonlogic.evaluator.JsonLogicExpression;

public class InequalityExpression implements JsonLogicExpression {
    public static final InequalityExpression INSTANCE = new InequalityExpression(EqualityExpression.INSTANCE);

    private final EqualityExpression delegate;

    private InequalityExpression(EqualityExpression delegate) {
        this.delegate = delegate;
    }

    @Override
    public String key() {
        return "!=";
    }

    @Override
    public Object evaluate(JsonLogicEvaluator evaluator, JsonLogicArray arguments, Object data)
            throws JsonLogicEvaluationException {
        boolean result = (boolean) delegate.evaluate(evaluator, arguments, data);

        return !result;
    }
}
