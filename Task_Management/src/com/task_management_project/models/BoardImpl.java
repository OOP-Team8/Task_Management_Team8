package com.task_management_project.models;

import com.task_management_project.models.contracts.Board;
import com.task_management_project.models.contracts.EventLog;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.utils.Validation;
import com.task_management_project.utils.contracts.DataValidation;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private String name;
    private List<Task> boardTasks;
    private List<EventLog> eventLogList;

    public BoardImpl(String name) {
        this.name = name;
        boardTasks = new ArrayList<>();
        eventLogList = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }
    private void setName(String name){
        Validation.validateStringLength(name, DataValidation.MIN_NAME_LENGTH, DataValidation.MAX_BOARD_LENGTH,DataValidation.BOARD_NAME_ERROR);
        this.name = name;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(boardTasks);
    }
}
