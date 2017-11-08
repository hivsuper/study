package org.lxp.java8;

import java.util.List;
import java.util.Stack;
import java.util.function.Function;

import org.lxp.vo.Student;

public class StudyFunction {
    public static int getTotalAge(List<Student> students) {
        return totalAgeFunction.apply(students);
    }

    private static Function<List<Student>, Integer> totalAgeFunction = students -> {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        students.forEach(student -> stack.push(stack.pop() + student.getAge()));
        return stack.pop();
    };

    public static int getTotalAgeViaReduce1(List<Student> students) {
        return students.stream().map(Student::getAge).reduce(0, (a, b) -> a + b);
    }

    public static int getTotalAgeViaReduce2(List<Student> students) {
        return students.stream().map(Student::getAge).reduce(Integer::sum).orElse(1000);
    }
}
