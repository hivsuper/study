package org.lxp.java8;

import java.util.List;

public class StudyFuncationalInterface implements TestInterface<List<String>, Void> {
    public void doTest(TestInterface<List<String>, Void> function, List<String> list) throws Exception {
        function.apply(list);
    }

    @Override
    public Void apply(List<String> list) throws Exception {
        throw new IllegalArgumentException();
    }
}

@FunctionalInterface
interface TestInterface<T, R> {
    R apply(List<String> list) throws Exception;
}