package org.lxp.design.pattern.chain.responsibility;

public class ManagerInterviewHandler extends AbstractInterviewHandler {

    public ManagerInterviewHandler(String name) {
        super(name);
    }

    @Override
    public int getLevel() {
        return AbstractInterviewHandler.LEVEL_MANAGER;
    }
}