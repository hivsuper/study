package org.lxp.java8.lambda;

import java.util.function.BiConsumer;

public class TestBiConsumer {
    private String biConsumerRtn;
    private String biConsumerLambdaRtn;
    private BiConsumer<Integer, String> biConsumer = new BiConsumer<Integer, String>() {
        @Override
        public void accept(Integer t, String u) {
            biConsumerRtn = String.format("%s%s", u, t);
        }
    };
    private BiConsumer<Integer, String> biConsumerLambda = (t, u) -> biConsumerLambdaRtn = String.format("%s%s", u, t);

    public String execute(int inputInt, String inputString) {
        execute(biConsumer, inputInt, inputString);
        return biConsumerRtn;
    }

    public String executeLambda(int inputInt, String inputString) {
        execute(biConsumerLambda, inputInt, inputString);
        return biConsumerLambdaRtn;
    }

    private void execute(BiConsumer<Integer, String> biConsumer, int inputInt, String inputString) {
        biConsumer.accept(inputInt, inputString);
    }
}
