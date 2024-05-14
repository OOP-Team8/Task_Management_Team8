package com.task_management_project.models;
import com.task_management_project.models.contracts.EventLog;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person {

    private String name;
    private final List<Task> tasks;


    public PersonImpl(String name, List<Task> tasks){
        this.name = name;
        this.tasks = tasks;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks) ;
    }

    @Override
    public List<EventLog> getEventLog() {
        return null;
    }
}
