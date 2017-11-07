package org.lxp.java8;

import static java.util.Comparator.comparingInt;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.lxp.vo.Student;

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

    public static Set<String> map(List<Student> list, Predicate<Student> predicate,
            Function<Student, String> function) {
        return list.stream().filter(predicate).map(function).collect(Collectors.toSet());
    }

    public static List<Student> sort(List<Student> list) {
        list.sort(comparingInt(Student::getAge).thenComparing(Student::getName).reversed());
        return list;
    }

    public static Map<Integer, List<Student>> groupby(List<Student> list) {
        return list.stream().collect(Collectors.groupingBy(Student::getAge));
    }

    public static List<Student> filter(List<Student> list, Predicate<Student> predicate1,
            Predicate<Student> predicate2) {
        return list.stream().filter(predicate1).filter(predicate2).collect(Collectors.toList());
    }

    public static List<Student> limit(List<Student> list, Predicate<Student> predicate, int limit) {
        return list.stream().filter(predicate).limit(limit).collect(Collectors.toList());
    }

    public static long count(List<Student> list, Predicate<Student> predicate) {
        return list.stream().filter(predicate).count();
    }
}
