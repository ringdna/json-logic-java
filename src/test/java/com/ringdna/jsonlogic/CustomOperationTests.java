package com.ringdna.jsonlogic;

import com.google.common.base.Function;
import org.junit.Assert;
import org.junit.Test;

public class CustomOperationTests {
    private static final JsonLogic jsonLogic = new JsonLogic();
    private static final Function<Object[], Object> FUNCTION = new Function<Object[], Object>() {
        @Override
        public Object apply(Object[] args) {
            return String.format("Hello %s!", args[0]);
        }
    };

    @Test
    public void testCustomOp() throws JsonLogicException {
        jsonLogic.addOperation("greet", FUNCTION);
        Assert.assertEquals("Hello json-logic!", jsonLogic.apply("{\"greet\": [\"json-logic\"]}", null));
    }

    @Test
    public void testCustomOpWithUppercaseLetter() throws JsonLogicException {
        jsonLogic.addOperation("Greet", FUNCTION);
        Assert.assertEquals("Hello json-logic!", jsonLogic.apply("{\"Greet\": [\"json-logic\"]}", null));
    }
}
