package org.lxp.java8;

import java.util.Collections;
import java.util.List;

public class StudyList {
    public static boolean removeIfNotEquals(List<String> list, String element) {
        return list.removeIf((e) -> {
            return e != element;
        });
    }

    public static boolean removeIfEquals(List<String> list, String element) {
        return list.removeIf((e) -> {
            return e == element;
        });
    }

    public static void plusOneAtFirstChar(List<String> list) {
        list.replaceAll(e -> String.valueOf((char) (e.charAt(0) + 1)));
    }

    public static void reverse(List<String> list) {
        Collections.reverse(list);
    }
}
