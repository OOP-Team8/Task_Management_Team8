package com.task_management_project.commands;

import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class ShowBoardActivity extends BaseCommand{
    public ShowBoardActivity(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        return null;
    }
}
