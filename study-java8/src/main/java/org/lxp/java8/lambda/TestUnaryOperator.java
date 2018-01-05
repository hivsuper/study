package org.lxp.java8.lambda;

import java.util.List;
import java.util.function.UnaryOperator;

public class TestUnaryOperator {
    private UnaryOperator<Integer> unaryOperator = new UnaryOperator<Integer>() {
        @Override
        public Integer apply(Integer t) {
            return t + 100;
        }
    };
    private UnaryOperator<Integer> unaryOperatorLambda = t -> t + 100;

    public void execute(List<Integer> list) {
        execute(unaryOperator, list);
    }

    public void executeLambda(List<Integer> list) {
        execute(unaryOperatorLambda, list);
    }

    private void execute(UnaryOperator<Integer> unaryOperator, List<Integer> list) {
        list.replaceAll(unaryOperator);
    }
}
