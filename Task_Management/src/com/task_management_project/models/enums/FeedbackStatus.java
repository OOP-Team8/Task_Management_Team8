package com.task_management_project.models.enums;

public enum FeedbackStatus {
    NEW,
    UNSCHEDULED,
    SCHEDULED,
    DONE;

    private FeedbackStatus() {
    }

    public String toString() {
        switch (this.ordinal()) {
            case 0:
                return "New";
            case 1:
                return "Ready";
            case 2:
                return "Scheduled";
            case 3:
                return "Done";
            default:
                return "";
        }
    }
}
