package com.task_management_project.models.contracts;

import com.task_management_project.models.TaskImpl;

import java.util.List;

public interface Person {

    String getName();
    List<Task> getTasks();
    List<EventLog> getEventLog();

}
