package org.lxp.java8;

import java.util.List;

public class StudyForeach {
    public static void print1(List<String> list) {
        list.forEach(s -> {
            System.out.println(s);
        });
    }

    public static void print2(List<String> list) {
        list.forEach(s -> System.out.println(s));
    }

    public static void print3(List<String> list) {
        list.forEach(System.out::println);
    }
}
