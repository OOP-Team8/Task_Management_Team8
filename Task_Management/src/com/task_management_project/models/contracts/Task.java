package com.task_management_project.models.contracts;


import com.task_management_project.models.enums.TaskType;

import java.util.List;

public interface Task extends Printable,Identifiable,Assignable{

    String getTitle();
    String getDescription();
    Person getPerson();

    TaskType getType();

    List<Comment> getCommentList();
    List<EventLog> getChangesList();

}
