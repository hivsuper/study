package org.lxp.design.pattern.observer;

import java.util.Observable;

public abstract class IBell extends Observable {
    public enum State {
        上课, 下课
    }

    public abstract void firstRing();

    public abstract void secondRing();
}
