package com.task_management_project.models.contracts;

import com.task_management_project.models.enums.FeedbackStatus;

public interface Feedback extends Task{
    int getRating();

    FeedbackStatus getStatus();
}
