package com.task_management_project.models.enums;

public enum BugStatus {
    ACTIVE,
    READY;
    @Override
    public String toString(){
        switch (this){
            case ACTIVE -> {
                return "Active";
            }
            case READY -> {
                return "Ready";
            }
            default -> {
                return "";
            }
        }
    }
}
