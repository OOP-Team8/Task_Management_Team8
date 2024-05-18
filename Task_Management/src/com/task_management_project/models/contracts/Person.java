package com.task_management_project.models.contracts;

public interface Person extends Nameable,Loggable,Taskable{

    void addTask(Task task);
}
