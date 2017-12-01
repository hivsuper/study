package org.lxp.design.pattern.observer;

public class Bell extends IBell {
    @Override
    public void firstRing() {
        System.out.println("上课铃响了！！！");
        super.setChanged();// 修改对象状态才能触发观察者的通知事件
        this.notifyObservers(State.上课);
    }

    @Override
    public void secondRing() {
        System.out.println("下课铃响了！！！");
        super.setChanged();// 修改对象状态才能触发观察者的通知事件
        this.notifyObservers(State.下课);
    }
}
