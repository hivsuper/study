package org.lxp.java8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.lxp.java8.StudyStream.Student;

public class StudyStreamTest {
    private List<Student> list = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        format.applyPattern("00");
        for (int i = 0; i < 12; i++) {
            Student student = new Student();
            student.setStudentNo("201701" + format.format(i));
            student.setGender(i % 2);
            student.setName("student_name_" + format.format(i));
            student.setAge(12 + i % 2);
            list.add(student);
        }
    }

    @Test
    public void testCollect() throws Exception {
        assertEquals(6, StudyStream.collect(list, (Student student) -> student.getAge() == 12).size());
    }

    @Test
    public void testFindFirst() throws Exception {
        assertEquals("student_name_00", StudyStream.findFirst(list, (Student student) -> student.getAge() == 12));
    }

    @Test
    public void testOrElse() throws Exception {
        assertNotNull(StudyStream.orElse(list, (Student student) -> student.getAge() == 14));
    }

    @Test
    public void testMap() throws Exception {
        assertEquals(12, StudyStream.map(list, (Student student) -> {
            return student.getName();
        }).size());
    }

}
