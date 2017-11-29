package org.lxp.design.pattern.chain.responsibility;

public class TeamLeaderInterviewHandler extends AbstractInterviewHandler {

    public TeamLeaderInterviewHandler(String name) {
        super(name);
    }

    @Override
    public int getLevel() {
        return AbstractInterviewHandler.LEVEL_TEAM_LEADER;
    }
}
