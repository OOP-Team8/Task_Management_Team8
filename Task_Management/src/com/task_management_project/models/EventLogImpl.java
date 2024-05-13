package com.task_management_project.models;

import com.task_management_project.models.contracts.Comment;
import com.task_management_project.models.contracts.EventLog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLogImpl implements EventLog {
    private Comment comment;
    private static LocalDateTime localDateTime;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");

    @Override
    public LocalDateTime getLocalDateTime() {
        return localDateTime = LocalDateTime.now();
    }
    public EventLogImpl(Comment comment){
        this.comment = comment;
    }
}
