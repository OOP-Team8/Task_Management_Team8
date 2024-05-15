package com.task_management_project.models;

import com.task_management_project.models.contracts.Board;
import com.task_management_project.models.contracts.EventLog;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private String name;
    private Person person;
    private List<Task> boardTasks;
    private List<EventLog> eventLogList;

    public BoardImpl(String name, List<Task> boardTasks,List<EventLog> eventLogList) {
        this.name = name;
        this.boardTasks = boardTasks;
        this.eventLogList = eventLogList;
    }

    public String getName() {
        return this.name;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(boardTasks);
    }
}
