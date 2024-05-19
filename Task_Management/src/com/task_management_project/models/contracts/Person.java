package com.task_management_project.models.contracts;

public interface Person extends Nameable,Loggable,Taskable{

    void addTask(Task task);
    void removeTask(Task task);
    void addEvent(EventLog log);
}
