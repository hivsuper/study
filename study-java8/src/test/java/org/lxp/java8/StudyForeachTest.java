package org.lxp.java8;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StudyForeachTest {
    private List<String> list;

    @Before
    public void setUp() throws Exception {
        list = Arrays.asList(new String[] { "111", "2222", "333333" });
    }

    @Test
    public void testPrint1() throws Exception {
        StudyForeach.print1(list);
    }

    @Test
    public void testPrint2() throws Exception {
        StudyForeach.print2(list);
    }

    @Test
    public void testPrint3() throws Exception {
        StudyForeach.print3(list);
    }

}
