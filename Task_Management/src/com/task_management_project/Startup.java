package com.task_management_project;

import com.task_management_project.core.TaskManagementEngineImpl;

public class Startup {
    public static void main(String[] args) {
        TaskManagementEngineImpl engine = new TaskManagementEngineImpl();
        engine.start();
    }
}