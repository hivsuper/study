package org.lxp.java8.lambda;

import java.util.List;
import java.util.function.Predicate;

public class TestPredicate {
    private Predicate<Integer> predicate = new Predicate<Integer>() {
        @Override
        public boolean test(Integer t) {
            return t > 100;
        }
    };
    private Predicate<Integer> predicateLambda = t -> t > 100;

    public void execute(List<Integer> list) {
        execute(predicate, list);
    }

    public void executeLambda(List<Integer> list) {
        execute(predicateLambda, list);
    }

    private void execute(Predicate<Integer> predicate, List<Integer> list) {
        list.removeIf(predicate);
    }
}
