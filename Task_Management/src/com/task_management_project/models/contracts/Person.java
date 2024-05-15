package com.task_management_project.models.contracts;

import java.util.List;

public interface Person {

    String getName();
    List<Task> getTasks();
    List<EventLog> getEventLog();

}
