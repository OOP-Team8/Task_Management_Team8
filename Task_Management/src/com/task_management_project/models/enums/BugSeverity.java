package com.task_management_project.models.enums;

public enum BugSeverity {
    CRITICAL,
    MAJOR,
    MINOR;

    @Override
    public String toString() {
        switch (this){
            case CRITICAL -> {
                return "Critical";
            }
            case MAJOR -> {
                return "Major";
            }
            case MINOR -> {
                return "Minor";
            }
            default -> {
                return "";
            }
        }
    }
}
