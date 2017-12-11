package org.lxp.java8;

import java.util.Optional;

public class StudyOptional<T> {
    public Optional<T> get(T t) {
        return Optional.of(t);
    }

    public Optional<T> getNullable(T t) {
        return Optional.ofNullable(t);
    }
}
