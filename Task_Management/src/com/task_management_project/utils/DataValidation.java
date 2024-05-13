package com.task_management_project.utils;

public interface DataValidation {
    int MIN_TITLE_LENGTH = 10;
    int MAX_TITLE_LENGTH = 100;
    int MIN_DESCRIPTION_LENGTH = 10;
    int MAX_DESCRIPTION_LENGTH = 500;
    String TITLE_ERROR = String.format("The title should be between %d and %d symbols",MIN_TITLE_LENGTH,MAX_TITLE_LENGTH);
    String DESCRIPTION_ERROR = String.format("The description should be between %d and %d symbols",MIN_DESCRIPTION_LENGTH,MAX_DESCRIPTION_LENGTH);
}
