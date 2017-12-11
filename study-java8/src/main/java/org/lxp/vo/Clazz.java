package org.lxp.vo;

import java.util.List;

public class Clazz {
    private String clazzName;
    private String teacherName;
    private List<Student> students;

    public Clazz(String clazzName, String teacherName, List<Student> students) {
        this.clazzName = clazzName;
        this.teacherName = teacherName;
        this.students = students;
    }

    public String getClazzName() {
        return clazzName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Clazz [clazzName=" + clazzName + ", teacherName=" + teacherName + ", students=" + students + "]";
    }
}
