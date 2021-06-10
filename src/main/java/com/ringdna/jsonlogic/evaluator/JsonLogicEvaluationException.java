package com.ringdna.jsonlogic.evaluator;

import com.ringdna.jsonlogic.JsonLogicException;

public class JsonLogicEvaluationException extends JsonLogicException {
    public JsonLogicEvaluationException(String msg) {
        super(msg);
    }

    public JsonLogicEvaluationException(Throwable cause) {
        super(cause);
    }

    public JsonLogicEvaluationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
