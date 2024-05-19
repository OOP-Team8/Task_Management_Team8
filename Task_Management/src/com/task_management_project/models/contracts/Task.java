package com.task_management_project.models.contracts;


import java.util.List;

public interface Task extends Printable,Identifiable{

    String getTitle();
    String getDescription();
    Person getPerson();
    List<Comment> getCommentList();
    List<EventLog> getChangesList();

}
