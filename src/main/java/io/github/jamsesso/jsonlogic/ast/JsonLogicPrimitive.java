package io.github.jamsesso.jsonlogic.ast;

public abstract class JsonLogicPrimitive<T> implements JsonLogicNode {
  public abstract T getValue();

  public abstract JsonLogicPrimitiveType getPrimitiveType();

  @Override
  public JsonLogicNodeType getType() {
    return JsonLogicNodeType.PRIMITIVE;
  }
}
