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
    private final List<Task> tasks;
    private final List<EventLog> history;

    public PersonImpl(String name) {
        setName(name);
        history = new ArrayList<>();
        tasks = new ArrayList<>();

    }

    @Override
    public String getName() {
        return this.name;
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
        tasks.add(task);
    }
    @Override
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    @Override
    public void addEvent(EventLog log) {
        history.add(log);
    }

    private void setName(String name) {
        Validation.validateStringLength(name, 5, 15, DataValidation.NAME_ERROR);
        this.name = name;
    }

    @Override
    public String getAsString() {
        return String.format("%s",getName());
    }
}
