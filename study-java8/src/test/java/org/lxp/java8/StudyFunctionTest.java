package org.lxp.java8;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.lxp.vo.Student;

public class StudyFunctionTest {

    private List<Student> list = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        format.applyPattern("00");
        for (int i = 0; i < 4; i++) {
            list.add(new Student("201701" + format.format(i), "student_name_" + format.format(i), i % 2, 11 + i));
        }
    }

    @Test
    public void testGetTotalAge() throws Exception {
        assertEquals(50, StudyFunction.getTotalAge(list), 0);
    }

    @Test
    public void testGetTotalAgeViaReduce() throws Exception {
        assertEquals(50, StudyFunction.getTotalAgeViaReduce1(list), 0);
        assertEquals(50, StudyFunction.getTotalAgeViaReduce2(list), 0);
        assertEquals(1000, StudyFunction.getTotalAgeViaReduce2(Collections.emptyList()), 0);
        assertEquals(50, StudyFunction.getTotalAgeViaSum(list), 0);
        assertEquals(50, StudyFunction.getTotalAgeViaSummarizingInt(list), 0);
    }

    @Test
    public void testGetYoungestStudent() throws Exception {
        assertEquals("Student [studentNo=20170100, name=student_name_00, gender=0, age=11]",
                StudyFunction.getYoungestStudentViaReduce(list).toString());
        assertEquals("Student [studentNo=20170100, name=student_name_00, gender=0, age=11]",
                StudyFunction.getYoungestStudentViaMinBy(list).toString());
    }

    @Test
    public void testGetOldestStudent() throws Exception {
        assertEquals("Student [studentNo=20170103, name=student_name_03, gender=1, age=14]",
                StudyFunction.getOldestStudentViaMax(list).toString());
        assertEquals("Student [studentNo=20170103, name=student_name_03, gender=1, age=14]",
                StudyFunction.getOldestStudentViaMaxBy(list).toString());
    }

    @Test
    public void testGetAverageAge() throws Exception {
        assertEquals(12.5, StudyFunction.getAverageAgeViaMapToInt(list), 0);
        assertEquals(12.5, StudyFunction.getAverageAgeViaAveragingInt(list), 0);
    }

}
