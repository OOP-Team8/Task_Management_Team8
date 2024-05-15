package com.task_management_project.models;

import com.task_management_project.models.contracts.Comment;
import com.task_management_project.models.contracts.EventLog;
import com.task_management_project.models.contracts.Feedback;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.models.enums.FeedbackStatus;
import com.task_management_project.utils.Validation;

import java.util.List;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private FeedbackStatus status;
    private int rating;

    public FeedbackImpl(int id, String title, String description, FeedbackStatus status, int rating) {
        super(id, title, description);
        this.status = status;
        this.setRating(rating);
        this.eventList.add(new EventLogImpl("It was created a new feedback!"));
    }

    public int getRating() {
        return this.rating;
    }

    private void setRating(int rating) {
        Validation.validateIntPositiveValue(rating, "The value should be positive");
        this.eventList.add(new EventLogImpl(String.format("The rating was changed to %d", rating)));
        this.rating = rating;
    }

    public FeedbackStatus getStatus() {
        return this.status;
    }

    //TODO

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public List<Comment> getCommentList() {
        return null;
    }

    @Override
    public List<EventLog> getChangesList() {
        return null;
    }
}
