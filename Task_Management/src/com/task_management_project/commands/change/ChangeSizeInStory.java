package com.task_management_project.commands.change;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;

import java.util.List;

public class ChangeSizeInStory extends BaseCommand {

    public ChangeSizeInStory(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        return null;
    }
}