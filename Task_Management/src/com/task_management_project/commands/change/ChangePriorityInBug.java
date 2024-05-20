package com.task_management_project.commands.change;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.utils.ParsingHelpers;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ChangePriorityInBug extends BaseCommand {
    private final int EXPECTED_PARAMS = 2;
    private final String ERROR_MESSAGE = "Wrong Id";

    public ChangePriorityInBug(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);

        Bug bug = TaskManagementRepositoryImpl.findById(ParsingHelpers.tryParseInt(parameters.get(0),ERROR_MESSAGE),getTaskManagementRepository().getBugs());
        Priority priority = Priority.valueOf(parameters.get(1));
        getTaskManagementRepository().changeBugPriority(bug, priority);

        return String.format("Bug priority on %s was changed to %s",bug.getTitle(), priority);
    }
}
