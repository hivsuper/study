package org.lxp.java8.lambda;

import java.util.function.Function;

public class TestFunction {
    private Function<String, Integer> function = new Function<String, Integer>() {
        @Override
        public Integer apply(String t) {
            return Integer.parseInt(t);
        }
    };
    private Function<String, Integer> functionLambda = Integer::parseInt;

    public int execute(String input) {
        return execute(function, input);
    }

    public int executeLambda(String input) {
        return execute(functionLambda, input);
    }

    private int execute(Function<String, Integer> function, String input) {
        return function.apply(input);
    }
}
