package com.task_management_project.utils;

import com.task_management_project.utils.contracts.DataValidation;

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
}
