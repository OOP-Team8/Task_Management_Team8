package com.task_management_project.utils;

public class Validation implements DataValidation{
    public static void validateStringLength(String value, int minLength, int maxLength, String errorMessage){
        if (value.length()<minLength || value.length()>maxLength){
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
