package com.task_management_project.models;

import com.task_management_project.models.contracts.Comment;
import com.task_management_project.models.contracts.EventLog;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.utils.contracts.DataValidation;
import com.task_management_project.utils.Validation;

import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.List;

public abstract class TaskImpl implements Task {
    private int id;
    private String title;
    private String description;
    protected List<Comment> comments = new ArrayList();
    protected List<EventLog> eventList = new ArrayList();
    Person person;

    public TaskImpl(int id, String title, String description, Person person) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        this.person = person;
    }

    @Override
    public Person getPerson() {
        return this.person;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        Validation.validateStringLength(title, 10, 100, DataValidation.TITLE_ERROR);
        if (this.title != null){
            this.eventList.add(new EventLogImpl(String.format("The title was changed - %s", title)));
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        Validation.validateStringLength(description, 10, 500, DataValidation.DESCRIPTION_ERROR);
        if (this.description != null){
            this.eventList.add(new EventLogImpl(String.format("The description was changed - %s", description)));
        }
        this.description = description;
    }

    public List<Comment> getCommentList() {
        return new ArrayList(comments);
    }

    public List<EventLog> getChangesList() {
        return new ArrayList(eventList);
    }
}
