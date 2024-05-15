package com.task_management_project.models;

import com.task_management_project.models.contracts.Comment;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.utils.contracts.DataValidation;
import com.task_management_project.utils.Validation;

public class CommentImpl implements Comment {
    private final String author;
    private String content;

    public CommentImpl(Person person, String content) {
        this.author = person.getName();
        this.setContent(content);
    }

    public String getContent() {
        return this.content;
    }

    private void setContent(String content) {
        Validation.validateStringLength(content, 10, 100, DataValidation.DESCRIPTION_ERROR);
        this.content = content;
    }

    public String getAuthor() {
        return this.author;
    }
}
