package com.task_management_project.models.contracts;

import com.task_management_project.models.TaskImpl;

import java.util.List;

public interface Board {

    String getName();
    List<Task> getTasks();
}
