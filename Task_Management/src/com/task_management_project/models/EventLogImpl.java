package com.task_management_project.models;

import com.task_management_project.models.contracts.Comment;
import com.task_management_project.models.contracts.EventLog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventLogImpl implements EventLog {
    private String description;
    private static LocalDateTime localDateTime;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");

    public static LocalDateTime getLocalDateTime() {
        return localDateTime = LocalDateTime.now();
    }

    public EventLogImpl(String description) {
        this.description = description;
    }

    @Override
    public String viewInfo() {
        String string = String.format("%s %s", getLocalDateTime().format(formatter), this.description);
        if (!string.isEmpty()) {
            return string;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
