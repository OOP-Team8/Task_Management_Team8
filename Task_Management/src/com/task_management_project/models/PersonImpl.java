package com.task_management_project.models;
import com.task_management_project.models.contracts.EventLog;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.utils.contracts.DataValidation;
import com.task_management_project.utils.Validation;

import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person {

    private String name;
    private List<Task> tasks = new ArrayList<>();
    private List<EventLog> history = new ArrayList<>();

    public PersonImpl(String name) {
        setName(name);
        history.add(new EventLogImpl(String.format("New employee was appointed - %s",this.name)));
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        Validation.validateStringLength(name, 5, 15, DataValidation.NAME_ERROR);
        this.name = name;
    }
    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
    @Override
    public List<EventLog> getLogs() {
        return new ArrayList<>(history);
    }

    @Override
    public void addTask(Task task) {

    }
}
