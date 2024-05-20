package com.task_management_project.utils;

import com.task_management_project.models.contracts.*;
import com.task_management_project.utils.contracts.DataValidation;

import java.util.List;

public class Validation implements DataValidation {
    public Validation() {
    }

    public static void validateStringLength(String value, int minLength, int maxLength, String errorMessage) {
        if (value.length() < minLength || value.length() > maxLength) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateIntPositiveValue(int value, String errormessage) {
        if (value < 0) {
            throw new IllegalArgumentException(errormessage);
        }
    }
    public static void validateArgumentsCount(List<String> list, int expectedArgumentsCount) {
        if (list.size() != expectedArgumentsCount) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedArgumentsCount, list.size()));
        }
    }
    public static <T extends Nameable> void validateDubs(List<T> list, String name){
        for (T item : list) {
            if (item.getName().equals(name)){
                throw new IllegalArgumentException(NOT_UNIQUE_NAME);
            }
        }
    }
    public static <T extends Taskable> StringBuilder message(StringBuilder builder, T value, List<Task> list){
        for (Task item : list) {
            builder.append(value.getTasks().indexOf(item) +1).append(". ID - ").append(item.getId()).append(" ").append(item.getTitle()).append(" ").append(item.getPerson().getName()).append(" - ").append(item.getClass().getInterfaces()[0].getSimpleName()).append(value.getTasks().indexOf(item) + 1 < value.getTasks().size() ? System.lineSeparator() : "");
        }
        return builder;
    }
}
