package com.ringdna.jsonlogic.evaluator.expressions;

import com.ringdna.jsonlogic.JsonLogic;
import com.ringdna.jsonlogic.ast.JsonLogicArray;
import com.ringdna.jsonlogic.ast.JsonLogicNode;
import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluationException;
import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluator;
import com.ringdna.jsonlogic.evaluator.JsonLogicExpression;

public class LogicExpression implements JsonLogicExpression {
    public static final LogicExpression AND = new LogicExpression(true);
    public static final LogicExpression OR = new LogicExpression(false);

    private final boolean isAnd;

    private LogicExpression(boolean isAnd) {
        this.isAnd = isAnd;
    }

    @Override
    public String key() {
        return isAnd ? "and" : "or";
    }

    @Override
    public Object evaluate(JsonLogicEvaluator evaluator, JsonLogicArray arguments, Object data)
            throws JsonLogicEvaluationException {
        if (arguments.size() < 1) {
            throw new JsonLogicEvaluationException("and operator expects at least 1 argument");
        }

        Object result = null;

        for (JsonLogicNode element : arguments) {
            result = evaluator.evaluate(element, data);

            if (isAnd ? !JsonLogic.truthy(result) : JsonLogic.truthy(result)) {
                return result;
            }
        }

        return result;
    }
}
