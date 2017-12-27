package org.lxp.java8;

import java.util.Map;

public class StudyMap {
    public static int computeIfAbsent(Map<String, Integer> map, String key) {
        return map.computeIfAbsent(key, k -> 0);
    }

    public static int computeIfPresent(Map<String, Integer> map, String key) {
        return map.computeIfPresent(key, (k, v) -> v + 1);
    }

    public static int compute(Map<String, Integer> map, String key) {
        return map.compute(key, (k, v) -> (v == null) ? 0 : v + 1);
    }

    public static Integer putIfAbsent(Map<String, Integer> map, String key, int value) {
        return map.putIfAbsent(key, value);
    }
}
