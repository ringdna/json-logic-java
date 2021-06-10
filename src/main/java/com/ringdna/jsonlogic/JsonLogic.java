package com.ringdna.jsonlogic;

import com.ringdna.jsonlogic.ast.JsonLogicNode;
import com.ringdna.jsonlogic.ast.JsonLogicParser;
import com.ringdna.jsonlogic.evaluator.JsonLogicEvaluator;
import com.ringdna.jsonlogic.evaluator.JsonLogicExpression;
import com.ringdna.jsonlogic.evaluator.expressions.AllExpression;
import com.ringdna.jsonlogic.evaluator.expressions.ArrayHasExpression;
import com.ringdna.jsonlogic.evaluator.expressions.ConcatenateExpression;
import com.ringdna.jsonlogic.evaluator.expressions.EqualityExpression;
import com.ringdna.jsonlogic.evaluator.expressions.FilterExpression;
import com.ringdna.jsonlogic.evaluator.expressions.IfExpression;
import com.ringdna.jsonlogic.evaluator.expressions.InExpression;
import com.ringdna.jsonlogic.evaluator.expressions.InequalityExpression;
import com.ringdna.jsonlogic.evaluator.expressions.LogExpression;
import com.ringdna.jsonlogic.evaluator.expressions.LogicExpression;
import com.ringdna.jsonlogic.evaluator.expressions.MapExpression;
import com.ringdna.jsonlogic.evaluator.expressions.MathExpression;
import com.ringdna.jsonlogic.evaluator.expressions.MergeExpression;
import com.ringdna.jsonlogic.evaluator.expressions.MissingExpression;
import com.ringdna.jsonlogic.evaluator.expressions.NotExpression;
import com.ringdna.jsonlogic.evaluator.expressions.NumericComparisonExpression;
import com.ringdna.jsonlogic.evaluator.expressions.PreEvaluatedArgumentsExpression;
import com.ringdna.jsonlogic.evaluator.expressions.ReduceExpression;
import com.ringdna.jsonlogic.evaluator.expressions.StrictEqualityExpression;
import com.ringdna.jsonlogic.evaluator.expressions.StrictInequalityExpression;
import com.ringdna.jsonlogic.evaluator.expressions.SubstringExpression;
import com.google.common.base.Function;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class JsonLogic {
    private final List<JsonLogicExpression> expressions;
    private final Map<String, JsonLogicNode> parseCache;
    private JsonLogicEvaluator evaluator;

    public JsonLogic() {
        this.expressions = new ArrayList<>();
        this.parseCache = new ConcurrentHashMap<>();

        // Add default operations
        addOperation(MathExpression.ADD);
        addOperation(MathExpression.SUBTRACT);
        addOperation(MathExpression.MULTIPLY);
        addOperation(MathExpression.DIVIDE);
        addOperation(MathExpression.MODULO);
        addOperation(MathExpression.MIN);
        addOperation(MathExpression.MAX);
        addOperation(NumericComparisonExpression.GT);
        addOperation(NumericComparisonExpression.GTE);
        addOperation(NumericComparisonExpression.LT);
        addOperation(NumericComparisonExpression.LTE);
        addOperation(IfExpression.IF);
        addOperation(IfExpression.TERNARY);
        addOperation(EqualityExpression.INSTANCE);
        addOperation(InequalityExpression.INSTANCE);
        addOperation(StrictEqualityExpression.INSTANCE);
        addOperation(StrictInequalityExpression.INSTANCE);
        addOperation(NotExpression.SINGLE);
        addOperation(NotExpression.DOUBLE);
        addOperation(LogicExpression.AND);
        addOperation(LogicExpression.OR);
        addOperation(LogExpression.STDOUT);
        addOperation(MapExpression.INSTANCE);
        addOperation(FilterExpression.INSTANCE);
        addOperation(ReduceExpression.INSTANCE);
        addOperation(AllExpression.INSTANCE);
        addOperation(ArrayHasExpression.SOME);
        addOperation(ArrayHasExpression.NONE);
        addOperation(MergeExpression.INSTANCE);
        addOperation(InExpression.INSTANCE);
        addOperation(ConcatenateExpression.INSTANCE);
        addOperation(SubstringExpression.INSTANCE);
        addOperation(MissingExpression.ALL);
        addOperation(MissingExpression.SOME);
    }

    public static boolean truthy(Object value) {
        if (value == null) {
            return false;
        }

        if (value instanceof Boolean) {
            return (boolean) value;
        }

        if (value instanceof Number) {
            if (value instanceof Double) {
                Double d = (Double) value;

                if (d.isNaN()) {
                    return false;
                } else if (d.isInfinite()) {
                    return true;
                }
            }

            if (value instanceof Float) {
                Float f = (Float) value;

                if (f.isNaN()) {
                    return false;
                } else if (f.isInfinite()) {
                    return true;
                }
            }

            return ((Number) value).doubleValue() != 0.0;
        }

        if (value instanceof String) {
            return !((String) value).isEmpty();
        }

        if (value instanceof Collection) {
            return !((Collection) value).isEmpty();
        }

        if (value.getClass().isArray()) {
            return Array.getLength(value) > 0;
        }

        return true;
    }

    public JsonLogic addOperation(final String name, final Function<Object[], Object> function) {
        return addOperation(new PreEvaluatedArgumentsExpression() {
            @Override
            public Object evaluate(List arguments, Object data) {
                return function.apply(arguments.toArray());
            }

            @Override
            public String key() {
                return name;
            }
        });
    }

    public JsonLogic addOperation(JsonLogicExpression expression) {
        expressions.add(expression);
        evaluator = null;

        return this;
    }

    public Object apply(String json, Object data) throws JsonLogicException {
        if (!parseCache.containsKey(json)) {
            parseCache.put(json, JsonLogicParser.parse(json));
        }

        if (evaluator == null) {
            evaluator = new JsonLogicEvaluator(expressions);
        }

        return evaluator.evaluate(parseCache.get(json), data);
    }
}
