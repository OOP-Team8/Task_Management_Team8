package com.task_management_project.commands;

import com.task_management_project.commands.contracts.Command;
import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public abstract class BaseCommand implements Command {
    private final TaskManagementRepository taskManagementRepository;

    protected BaseCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    protected TaskManagementRepository getTaskManagementRepository() {
        return taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return executeCommand(parameters);
    }

    protected abstract String executeCommand(List<String> parameters);
}
