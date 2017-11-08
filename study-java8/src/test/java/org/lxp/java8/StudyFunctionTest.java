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
            Student student = new Student();
            student.setStudentNo("201701" + format.format(i));
            student.setGender(i % 2);
            student.setName("student_name_" + format.format(i));
            student.setAge(12 + i % 2);
            list.add(student);
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
    }

}
