package org.lxp.design.pattern.observer;

import org.junit.Test;

public class ObserverTest {
    @Test
    public void doTest() {
        Student A = new Student("A");
        Student B = new Student("B");
        Student C = new Student("C");
        Student D = new Student("D");
        Bell bell = new Bell();
        bell.addObserver(D);
        bell.addObserver(C);
        bell.addObserver(B);
        bell.addObserver(A);
        bell.firstRing();
        bell.secondRing();
    }
}
