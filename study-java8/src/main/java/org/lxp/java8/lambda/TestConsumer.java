package org.lxp.java8.lambda;

import java.util.function.Consumer;

public class TestConsumer {
    private int consumerRtn;
    private int consumerLambdaRtn;
    private Consumer<Integer> consumer = new Consumer<Integer>() {
        @Override
        public void accept(Integer t) {
            consumerRtn = t + 1;
        }
    };
    private Consumer<Integer> consumerLambda = t -> consumerLambdaRtn = t;

    public int execute(int input) {
        execute(consumer, input);
        return consumerRtn;
    }

    public int executeLambda(int input) {
        execute(consumerLambda, input);
        return consumerLambdaRtn;
    }

    private void execute(Consumer<Integer> consumer, int input) {
        consumer.accept(input);
    }
}
