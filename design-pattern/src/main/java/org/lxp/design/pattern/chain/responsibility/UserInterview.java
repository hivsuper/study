package org.lxp.design.pattern.chain.responsibility;

public class UserInterview {
    private int level;
    private boolean isApproved;

    public UserInterview(int level) {
        this.level = level;
        this.isApproved = false;
    }

    public int getLevel() {
        return level;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void approve(String executor) {
        this.isApproved = true;
        System.out.println(String.format("%s approve the applition", executor));
    }

    @Override
    public String toString() {
        return "UserApplication [level=" + level + "]";
    }
}
