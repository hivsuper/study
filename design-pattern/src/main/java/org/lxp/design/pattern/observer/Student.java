package org.lxp.design.pattern.observer;

public class Student extends IStudent {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void enterClassRoom() {
        System.out.println("Student " + this.name + " enters classroom");
    }

    @Override
    public void leaveClassRoom() {
        System.out.println("Student " + this.name + " leaves classroom");
    }
}
