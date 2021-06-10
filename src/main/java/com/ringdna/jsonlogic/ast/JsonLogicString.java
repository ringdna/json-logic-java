package com.ringdna.jsonlogic.ast;

public class JsonLogicString extends JsonLogicPrimitive<String> {
    private final String value;

    public JsonLogicString(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public JsonLogicPrimitiveType getPrimitiveType() {
        return JsonLogicPrimitiveType.STRING;
    }
}
