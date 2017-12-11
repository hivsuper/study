package org.lxp.vo;

import java.util.List;

public class Clazz {
    private String clazzName;
    private String teacherName;
    private List<Student> students;

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Clazz [clazzName=" + clazzName + ", teacherName=" + teacherName + ", students=" + students + "]";
    }
}
