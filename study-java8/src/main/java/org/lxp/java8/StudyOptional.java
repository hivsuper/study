package org.lxp.java8;

import java.util.Optional;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.lxp.vo.Clazz;
import org.lxp.vo.Student;

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

    public String getClazzNameViaOrElseGet(Clazz clazz, String defaultValue) {
        Optional<Clazz> optional = getNullable(clazz);
        Random r = new Random();
        return r.nextBoolean() ? optional.map(Clazz::getClazzName).orElseGet(() -> defaultValue)
                : optional.map(Clazz::getClazzName).orElse(defaultValue);
    }

    public String getFirstStudentNoInClazz(Clazz clazz) {
        Optional<Clazz> optional = getNullable(clazz);
        return optional.filter(c -> StringUtils.isNotEmpty(c.getTeacherName())).map(Clazz::getStudents)
                .flatMap(students -> {
                    return students.stream().findFirst();
                }).map(Student::getStudentNo).orElse(null);
    }
}
