package com.ringdna.jsonlogic.ast;

public class JsonLogicBoolean extends JsonLogicPrimitive<Boolean> {
    public static final JsonLogicBoolean TRUE = new JsonLogicBoolean(true);
    public static final JsonLogicBoolean FALSE = new JsonLogicBoolean(false);

    private final boolean value;

    public JsonLogicBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public JsonLogicPrimitiveType getPrimitiveType() {
        return JsonLogicPrimitiveType.BOOLEAN;
    }
}
