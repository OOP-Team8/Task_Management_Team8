package com.task_management_project.models.enums;

public enum TaskType {
    BUG,
    FEEDBACK,
    STORY;

    @Override
    public String toString() {
        switch (this) {
            case BUG:
                return "Bug";
            case FEEDBACK:
                return "Feedback";
            case STORY:
                return "Story";
            default:
                throw new UnsupportedOperationException("Can't convert task type.");
        }
    }
}
