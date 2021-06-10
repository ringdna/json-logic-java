package com.ringdna.jsonlogic.ast;

public class JsonLogicNumber extends JsonLogicPrimitive<Double> {
    private final Number value;

    public JsonLogicNumber(Number value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value.doubleValue();
    }

    @Override
    public JsonLogicPrimitiveType getPrimitiveType() {
        return JsonLogicPrimitiveType.NUMBER;
    }
}
