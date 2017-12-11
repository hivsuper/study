package org.lxp.java8;

import java.util.Optional;

import org.lxp.vo.Clazz;

public class StudyOptional {
    public Optional<Clazz> get(Clazz clazz) {
        return Optional.of(clazz);
    }

    public Optional<Clazz> getNullable(Clazz clazz) {
        return Optional.ofNullable(clazz);
    }

    public Optional<String> getClazzNameViaMap(Clazz clazz) {
        Optional<Clazz> optional = getNullable(clazz);
        return optional.map(Clazz::getClazzName);
    }
}
