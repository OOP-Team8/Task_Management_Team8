package com.task_management_project.models.enums;

public enum BugSeverity {
    CRITICAL,
    HUGE,
    SMALL;

    @Override
    public String toString() {
        switch (this){
            case CRITICAL -> {
                return "Critical";
            }
            case HUGE -> {
                return "Huge";
            }
            case SMALL -> {
                return "Small";
            }
            default -> {
                return "";
            }
        }
    }
}
