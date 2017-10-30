package org.lxp.java8;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudyStream {

    public static List<Student> collect(List<Student> list, Predicate<Student> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public static String findFirst(List<Student> list, Predicate<Student> predicate) {
        return list.stream().filter(predicate).findFirst().get().getName();
    }

    public static Student orElse(List<Student> list, Predicate<Student> predicate) {
        return list.stream().filter(predicate).findFirst().orElse(new Student());
    }

    public static List<String> map(List<Student> list, Function<Student, String> function) {
        return list.stream().map(function).collect(Collectors.toList());
    }

    static class Student {
        private String studentNo;
        private String name;
        private int gender;
        private int age;

        public String getStudentNo() {
            return studentNo;
        }

        public void setStudentNo(String studentNo) {
            this.studentNo = studentNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return String.format("Student [studentNo=%s, name=%s, gender=%s, age=%s]", studentNo, name, gender, age);
        }
    }
}
