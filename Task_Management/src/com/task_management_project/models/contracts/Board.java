package com.task_management_project.models.contracts;

public interface Board extends Loggable,Taskable,Nameable {

    void addTask(Task task);
}
