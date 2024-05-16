package com.task_management_project.models.contracts;

import com.task_management_project.models.enums.FeedbackStatus;

public interface Feedback extends Task,Identifiable{
    int getRating();

    FeedbackStatus getStatus();
}
