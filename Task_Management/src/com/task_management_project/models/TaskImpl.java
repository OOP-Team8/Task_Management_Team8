package com.task_management_project.models;

import com.task_management_project.models.contracts.Comment;
import com.task_management_project.models.contracts.EventLog;

import java.util.List;

public class TaskImpl {
    private int id;
    private String title;
    private String description;
    private List<Comment> comments;
    private List<EventLog> eventList;
}
