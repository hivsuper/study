package org.lxp.java8;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StudyFuncationalInterfaceTest {
    private List<String> list;
    private StudyFuncationalInterface studyFuncationalInterface;

    @Before
    public void setUp() throws Exception {
        studyFuncationalInterface = new StudyFuncationalInterface();
        list = Arrays.asList(new String[] { "111" });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoTest() throws Exception {
        studyFuncationalInterface.doTest(studyFuncationalInterface, list);
    }

}
