package org.lxp.design.pattern.chain.responsibility;

public class CEOInterviewHandler extends AbstractInterviewHandler {

    public CEOInterviewHandler(String name) {
        super(name);
    }

    @Override
    public int getLevel() {
        return AbstractInterviewHandler.LEVEL_CEO;
    }
}