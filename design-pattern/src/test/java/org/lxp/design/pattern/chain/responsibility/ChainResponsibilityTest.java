package org.lxp.design.pattern.chain.responsibility;

import org.junit.Before;
import org.junit.Test;

public class ChainResponsibilityTest {
    private TeamLeaderInterviewHandler teamLeaderHandler = new TeamLeaderInterviewHandler("San Zhang");
    private ManagerInterviewHandler managerHandler = new ManagerInterviewHandler("Si Li");
    private CEOInterviewHandler ceoHandler = new CEOInterviewHandler("Wu Wang");

    @Before
    public void setUp() {
        teamLeaderHandler.setNextHandler(managerHandler);
        managerHandler.setNextHandler(ceoHandler);
    }

    @Test
    public void teamLeaderInterviewCommonEmployee() {
        UserInterview application = new UserInterview(AbstractInterviewHandler.LEVEL_COMMON_EMPLOYEE);
        teamLeaderHandler.handle(application);
    }

    @Test
    public void teamLeaderInterviewManager() {
        UserInterview application = new UserInterview(AbstractInterviewHandler.LEVEL_MANAGER);
        teamLeaderHandler.handle(application);
    }

    @Test
    public void ceoInterviewManager() {
        UserInterview application = new UserInterview(AbstractInterviewHandler.LEVEL_MANAGER);
        ceoHandler.handle(application);
    }
}
