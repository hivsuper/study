package org.lxp.design.pattern.chain.responsibility;

public abstract class AbstractInterviewHandler implements IHandler {
    public static final int LEVEL_COMMON_EMPLOYEE = 0;
    public static final int LEVEL_TEAM_LEADER = 1;
    public static final int LEVEL_MANAGER = 2;
    public static final int LEVEL_CEO = 3;
    private String name;
    private IHandler nextHandler;

    public AbstractInterviewHandler(String name) {
        this.name = name;
    }

    @Override
    public void handle(UserInterview application) {
        if (application.getLevel() >= LEVEL_CEO) {
            System.out.println("Sorry, no position found for " + application);
        } else {
            if (getLevel() > application.getLevel()) {
                application.approve(name);
            }
            if (this.nextHandler != null) {
                this.nextHandler.handle(application);
            } else {
                if (application.isApproved()) {
                    System.out.println("Send an offer for " + application);
                } else {
                    throw new IllegalAccessError();
                }
            }
        }
    }

    public void setNextHandler(IHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract int getLevel();
}