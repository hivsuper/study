package org.lxp.design.pattern.observer;

import java.util.Observable;
import java.util.Observer;

import org.lxp.design.pattern.observer.IBell.State;

public abstract class IStudent implements Observer {

    public abstract void enterClassRoom();

    public abstract void leaveClassRoom();

    @Override
    public void update(Observable o, Object arg) {
        switch (State.valueOf(arg.toString())) {
        case 上课:
            enterClassRoom();
            break;
        case 下课:
            leaveClassRoom();
            break;
        }
    }

}
