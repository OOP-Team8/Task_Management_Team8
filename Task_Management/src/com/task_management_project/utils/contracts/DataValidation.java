package com.task_management_project.utils.contracts;

public interface DataValidation {
    int MIN_TITLE_LENGTH = 10;
    int MAX_TITLE_LENGTH = 100;
    int MIN_DESCRIPTION_LENGTH = 10;
    int MAX_DESCRIPTION_LENGTH = 500;
    int MIN_NAME_LENGTH = 5;
    int MAX_NAME_LENGTH = 15;
    String TITLE_ERROR = String.format("The title should be between %d and %d symbols", 10, 100);
    String DESCRIPTION_ERROR = String.format("The description should be between %d and %d symbols", 10, 500);
    String NAME_ERROR = String.format("The name should be between %d and %d symbols", 5, 15);
    String POSITIVE_VALUE_ERROR = "The value should be positive";
}
