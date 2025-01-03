package com.task_management_project.models;

import com.task_management_project.models.contracts.*;
import com.task_management_project.models.enums.FeedbackStatus;
import com.task_management_project.models.enums.TaskType;
import com.task_management_project.utils.Validation;

import java.util.List;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private FeedbackStatus status;
    private int rating;

    public FeedbackImpl(int id, String title, String description, FeedbackStatus status, int rating, Person person) {
        super(id, title, description, person);
        this.status = status;
        this.type = TaskType.FEEDBACK;
        setRating(rating);
        this.eventList.add(new EventLogImpl("It was created a new feedback!"));
    }
    @Override
    public int getRating() {
        return this.rating;
    }
    @Override
    public FeedbackStatus getStatus() {
        return this.status;
    }

    private void setRating(int rating) {
        Validation.validateIntPositiveValue(rating, "The value should be positive");
        if (this.rating != 0) {
            this.eventList.add(new EventLogImpl(String.format("The rating was changed to %d", rating)));
        }
        this.rating = rating;
    }

    @Override
    public String getAsString() {
        return String.format("%s\nStatus: %s\nRating: %s",super.getAsString(),getStatus(),getRating());
    }

    @Override
    public boolean getAssignable() {
        return false;
    }
}
