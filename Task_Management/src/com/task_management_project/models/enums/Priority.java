package com.task_management_project.models.enums;

public enum Priority {
    HIGH,
    MEDIUM,
    LOW;

    @Override
    public String toString() {
        switch (this){
            case HIGH -> {
                return "High";
            }
            case MEDIUM -> {
                return "Medium";
            }
            case LOW -> {
                return "Low";
            }
            default -> {
                return "";
            }
        }
    }
}
