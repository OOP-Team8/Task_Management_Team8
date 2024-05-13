package com.task_management_project.models.contracts;

import java.util.List;

public interface Task {
    int getId();
    String getTitle();
    String getDescription();
    List<String> getCommentList();
    List<String> getChangesList();
}
