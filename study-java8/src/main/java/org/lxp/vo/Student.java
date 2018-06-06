package org.lxp.vo;

public class Student {
    private String studentNo;
    private String name;
    private int gender;
    private int age;

    public Student(String studentNo, String name, int gender, int age) {
        this.studentNo = studentNo;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("Student [studentNo=%s, name=%s, gender=%s, age=%s]", studentNo, name, gender, age);
    }
}
