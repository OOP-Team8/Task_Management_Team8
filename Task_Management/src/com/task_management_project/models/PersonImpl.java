package com.task_management_project.models;
import com.task_management_project.models.contracts.EventLog;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.utils.DataValidation;
import com.task_management_project.utils.Validation;

import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person {

    private String name;
    private List<Task> tasks;
    private List<EventLog> history;
    private List<String> names = new ArrayList();

    public PersonImpl(String name, List<Task> tasks, List<Story> history) {
        this.setName(name);
        new ArrayList();
        new ArrayList();
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (!this.names.isEmpty() && this.names.contains(name)) {
            throw new IllegalArgumentException();
        } else {
            Validation.validateStringLength(name, 5, 15, DataValidation.NAME_ERROR);
            this.names.add(name);
            this.name = name;
        }
    }

    public List<Task> getTasks() {
        return new ArrayList(this.tasks);
    }

    public List<EventLog> getEventLog() {
        return new ArrayList(this.history);
    }
}
