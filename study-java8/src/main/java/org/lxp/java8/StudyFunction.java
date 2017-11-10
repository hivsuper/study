package org.lxp.java8;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.lxp.vo.Student;

public class StudyFunction {
    /**
     * 利用function求和
     * 
     * @param students
     * @return
     */
    public static int getTotalAge(List<Student> students) {
        return totalAgeFunction.apply(students);
    }

    private static Function<List<Student>, Integer> totalAgeFunction = students -> {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        students.forEach(student -> stack.push(stack.pop() + student.getAge()));
        return stack.pop();
    };

    /**
     * 利用reduce求和方法一 最差
     * 
     * @param students
     * @return
     */
    public static int getTotalAgeViaReduce1(List<Student> students) {
        return students.stream().map(Student::getAge).reduce(0, (a, b) -> a + b);
    }

    /**
     * 利用reduce求和方法二 中
     * 
     * @param students
     * @return
     */
    public static int getTotalAgeViaReduce2(List<Student> students) {
        return students.stream().map(Student::getAge).reduce(Integer::sum).orElse(1000);
    }

    /**
     * 利用reduce求和方法三 最好
     * 
     * @param students
     * @return
     */
    public static int getTotalAgeViaSum(List<Student> students) {
        return students.stream().mapToInt(Student::getAge).sum();
    }

    /**
     * 利用summarizingInt求和方法四
     * 
     * @param students
     * @return
     */
    public static long getTotalAgeViaSummarizingInt(List<Student> students) {
        return students.stream().collect(Collectors.summarizingInt(Student::getAge)).getSum();
    }

    /**
     * 通过reduce取得年龄最小学生
     * 
     * @param students
     * @return
     */
    public static Student getYoungestStudentViaReduce(List<Student> students) {
        return students.stream().reduce((a, b) -> a.getAge() < b.getAge() ? a : b).orElse(null);
    }

    /**
     * 通过minBy取得年龄最小学生
     * 
     * @param students
     * @return
     */
    public static Student getYoungestStudentViaMinBy(List<Student> students) {
        return students.stream().collect(Collectors.minBy(Comparator.comparingInt(Student::getAge))).orElse(null);
    }

    public static Student getOldestStudentViaMax(List<Student> students) {
        return students.stream().max(Comparator.comparing(Student::getAge)).orElse(null);
    }

    public static Student getOldestStudentViaMaxBy(List<Student> students) {
        return students.stream().collect(Collectors.maxBy(Comparator.comparingInt(Student::getAge))).orElse(null);
    }

    public static double getAverageAgeViaMapToInt(List<Student> students) {
        return students.stream().mapToInt(Student::getAge).average().orElse(0);
    }

    public static double getAverageAgeViaAveragingInt(List<Student> students) {
        return students.stream().collect(Collectors.averagingInt(Student::getAge));
    }
}
