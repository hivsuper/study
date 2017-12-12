package org.lxp.java8;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lxp.vo.Clazz;
import org.lxp.vo.Student;

public class StudyOptionalTest {
    private static final String clazzName = "一年一班";
    private final StudyOptional optional = new StudyOptional();
    private Clazz clazz;

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
        clazz = new Clazz(clazzName, "Mr Lee", list);
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
        Assert.assertFalse(optional.getNullable(null).isPresent());
        Assert.assertEquals(Optional.empty(), optional.getNullable(null));
    }

    @Test
    public void getNullableWhenNotNull() throws Exception {
        Assert.assertEquals(clazz, optional.getNullable(clazz).get());
    }

    @Test
    public void testGetClazzNameViaMapWhenNotNull() throws Exception {
        Assert.assertEquals(clazzName, optional.getClazzNameViaMap(clazz).get());
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetClazzNameViaMapWhenNull() throws Exception {
        optional.getClazzNameViaMap(null).get();
    }

    @Test
    public void testGetClazzNameViaOrElseGet() throws Exception {
        String defaultValue = "defaultValue";
        Assert.assertEquals(clazzName, optional.getClazzNameViaOrElseGet(clazz, defaultValue));
        Assert.assertEquals(defaultValue, optional.getClazzNameViaOrElseGet(null, defaultValue));
    }

    @Test
    public void testGetFirstStudentNoInClazz() throws Exception {
        Assert.assertEquals("20170100", optional.getFirstStudentNoInClazz(clazz));
        Assert.assertNull(optional.getFirstStudentNoInClazz(null));
    }

    @Test
    public void testMapAndOrElseGet() throws Exception {
        Optional<String> _null1 = Optional.ofNullable(null);
        Optional<String> _111 = Optional.ofNullable("111");
        Optional<String> _222 = Optional.ofNullable("222");
        Optional<String> _null2 = Optional.ofNullable(null);
        Assert.assertEquals("222", _null1.map(s -> s).orElseGet(() -> _222.map(s -> s).orElse("333")));
        Assert.assertEquals("111", _111.map(s -> s).orElseGet(() -> _222.map(s -> s).orElse("333")));
        Assert.assertEquals("333", _null1.map(s -> s).orElseGet(() -> _null2.map(s -> s).orElse("333")));
    }

    @Test
    public void testIfPresent() throws Exception {
        StringBuilder sb = new StringBuilder();
        Optional<String> _null1 = Optional.ofNullable(null);
        _null1.ifPresent(x -> sb.append("111"));
        Assert.assertTrue(sb.length() == 0);
    }
}
