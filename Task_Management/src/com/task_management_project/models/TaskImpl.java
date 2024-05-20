package com.task_management_project.models;

import com.task_management_project.models.contracts.Comment;
import com.task_management_project.models.contracts.EventLog;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.models.enums.TaskType;
import com.task_management_project.utils.contracts.DataValidation;
import com.task_management_project.utils.Validation;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskImpl implements Task {
    private int id;
    private String title;
    private String description;
    protected List<Comment> comments;
    protected List<EventLog> eventList;
    private final Person person;
    protected TaskType type;

    public TaskImpl(int id, String title, String description, Person person) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        comments = new ArrayList();
        eventList = new ArrayList();
        this.person = person;
    }

    @Override
    public Person getPerson() {
        return this.person;
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public TaskType getType(){
        return type;
    }
    @Override
    public List<Comment> getCommentList() {
        return new ArrayList(comments);
    }
    @Override
    public List<EventLog> getChangesList() {
        return new ArrayList(eventList);
    }


    private void setTitle(String title) {
        Validation.validateStringLength(title, 10, 100, DataValidation.TITLE_ERROR);
        if (this.title != null){
            this.eventList.add(new EventLogImpl(String.format("The title was changed - %s", title)));
        }
        this.title = title;
    }

    private void setDescription(String description) {
        Validation.validateStringLength(description, 10, 500, DataValidation.DESCRIPTION_ERROR);
        if (this.description != null){
            this.eventList.add(new EventLogImpl(String.format("The description was changed - %s", description)));
        }
        this.description = description;
    }



    @Override
    public String getAsString() {
        return String.format("%d\n%s\n%s\n%s\n%s",getId(),getTitle(),getDescription(),getPerson().getAsString(),getType());
    }
}
