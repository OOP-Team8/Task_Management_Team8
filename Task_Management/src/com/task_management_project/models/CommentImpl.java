package com.task_management_project.models;

import com.task_management_project.models.contracts.Comment;
import com.task_management_project.utils.DataValidation;
import com.task_management_project.utils.Validation;

public class CommentImpl implements Comment {
    private String author;
    private String content;

    public CommentImpl(String author, String content){
        this.author = author;
        setContent(content);
    }
    @Override
    public String getContent() {
        return this.content;
    }
    private void setContent(String content){
        Validation.validateStringLength(content, DataValidation.MIN_DESCRIPTION_LENGTH,DataValidation.MAX_TITLE_LENGTH,DataValidation.DESCRIPTION_ERROR);
        this.content = content;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }
}
