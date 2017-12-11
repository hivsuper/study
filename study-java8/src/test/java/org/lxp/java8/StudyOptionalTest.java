package org.lxp.java8;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lxp.vo.Clazz;
import org.lxp.vo.Student;

public class StudyOptionalTest {
    private final StudyOptional<Clazz> optional = new StudyOptional<>();
    private final Clazz clazz = new Clazz();

    @Before
    public void setUp() throws Exception {
        int MAX_SIZE = 5;
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < MAX_SIZE; i++) {
            DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
            format.applyPattern("00");
            Student student = new Student();
            student.setStudentNo("201701" + format.format(i));
            student.setGender(i % 2);
            student.setName("student_name_" + format.format(i));
            student.setAge(12 + i % 2);
            list.add(student);
        }
        clazz.setStudents(list);
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerException() throws Exception {
        optional.get(null);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(clazz, optional.get(clazz).get());
    }

    @Test
    public void getNullableWhenNull() throws Exception {
        Assert.assertNull(optional.getNullable(null));
    }

    @Test
    public void getNullableWhenNotNull() throws Exception {
        Assert.assertEquals(clazz, optional.getNullable(clazz).get());
    }

}
