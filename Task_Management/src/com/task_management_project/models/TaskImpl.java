package com.task_management_project.models;

import com.task_management_project.models.contracts.Comment;
import com.task_management_project.models.contracts.EventLog;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.utils.contracts.DataValidation;
import com.task_management_project.utils.Validation;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskImpl implements Task {
    private int id = 0;
    private String title;
    private String description;
    protected List<Comment> comments = new ArrayList();
    protected List<EventLog> eventList = new ArrayList();

    public TaskImpl(int id, String title, String description) {
        this.id = id;
        this.setTitle(title);
        this.setDescription(description);
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    private void setTitle(String title) {
        Validation.validateStringLength(title, 10, 100, DataValidation.TITLE_ERROR);
        this.eventList.add(new EventLogImpl(String.format("The title was changed - %s", title)));
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    private void setDescription(String description) {
        Validation.validateStringLength(description, 10, 500, DataValidation.DESCRIPTION_ERROR);
        this.eventList.add(new EventLogImpl(String.format("The description was changed - %s", description)));
        this.description = description;
    }

    public List<Comment> getCommentList() {
        return new ArrayList(this.comments);
    }

    public List<EventLog> getChangesList() {
        return new ArrayList(this.eventList);
    }
}
