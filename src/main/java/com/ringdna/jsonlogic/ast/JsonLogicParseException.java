package com.ringdna.jsonlogic.ast;

import com.ringdna.jsonlogic.JsonLogicException;

public class JsonLogicParseException extends JsonLogicException {
    public JsonLogicParseException(String msg) {
        super(msg);
    }

    public JsonLogicParseException(Throwable cause) {
        super(cause);
    }

    public JsonLogicParseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
