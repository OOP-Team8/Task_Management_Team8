package com.task_management_project.commands;

import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class AddPersonToTeam extends BaseCommand {
    public AddPersonToTeam(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        return null;
    }
}
