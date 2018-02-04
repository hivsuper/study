package org.lxp.java8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.lxp.vo.Student;

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
        assertEquals(6, StudyStream.collect(list, student -> student.getAge() == 12).size());
    }

    @Test
    public void testFindFirst() throws Exception {
        assertEquals("student_name_00", StudyStream.findFirst(list, student -> student.getAge() == 12));
    }

    @Test
    public void testOrElse() throws Exception {
        assertNotNull(StudyStream.orElse(list, student -> student.getAge() == 14));
    }

    @Test
    public void testMap() throws Exception {
        assertEquals(12, StudyStream.map(list, Student::getName).size());
        assertEquals(6, StudyStream.map(list, student -> student.getAge() == 12, Student::getName).size());
    }

    @Test
    public void testSort() throws Exception {
        assertEquals("Student [studentNo=20170111, name=student_name_11, gender=1, age=13]",
                StudyStream.sort(list).get(0).toString());
    }

    @Test
    public void testGroupby() throws Exception {
        assertEquals(6, StudyStream.groupby(list).get(12).size());
    }

    @Test
    public void testGroupbyToSet() throws Exception {
        List<Student> students = new ArrayList<>(list);
        students.addAll(list);
        assertEquals(24, students.size());
        assertEquals(6, StudyStream.groupbyToSet(students).get(12).size());
        assertEquals(12, StudyStream.groupbyToSet(students).entrySet().stream()
                .mapToInt(entry -> entry.getValue().size()).sum());
    }

    @Test
    public void testToMap() throws Exception {
        assertEquals(6, StudyStream.toMap1(list).size());
        assertEquals(13, StudyStream.toMap1(list).get("20170111").getAge());
        assertEquals("student_name_11", StudyStream.toMap2(list).get("20170111"));
    }

    @Test
    public void testFilter() throws Exception {
        assertEquals(6, StudyStream.filter(list, student -> student.getAge() == 13, student -> student.getGender() == 1)
                .size());
    }

    @Test
    public void testLimit() throws Exception {
        assertTrue(StudyStream.limit(list, student -> student.getGender() == 1, 3).stream()
                .allMatch(student -> student.getGender() == 1));
    }

    @Test
    public void testCount() throws Exception {
        assertEquals(6, StudyStream.count(list, student -> student.getGender() == 1));
    }

    @Test
    public void testFlatMap() throws Exception {
        String[] array1 = { "a", "b" };
        String[] array2 = { "c", "d" };
        String[] array3 = { "e", "f" };
        assertEquals("[A, B, C, D, E, F]", StudyStream.flatMap(array1, array2, array3).toString());
    }

}
