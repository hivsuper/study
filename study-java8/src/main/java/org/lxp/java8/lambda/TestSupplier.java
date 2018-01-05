package org.lxp.java8.lambda;

import java.util.function.Supplier;

public class TestSupplier {
    private Supplier<Integer> supplier = new Supplier<Integer>() {
        @Override
        public Integer get() {
            return 100;
        }
    };
    private Supplier<Integer> supplierLambda = () -> 100;

    public int execute() {
        return execute(supplier);
    }

    public int executeLambda() {
        return execute(supplierLambda);
    }

    private int execute(Supplier<Integer> supplier) {
        return supplier.get();
    }
}
