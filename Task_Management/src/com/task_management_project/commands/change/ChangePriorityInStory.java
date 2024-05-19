package com.task_management_project.commands.change;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ChangePriorityInStory extends BaseCommand {
    private final int EXPECTED_PARAMS = 2;
    public ChangePriorityInStory(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);

        Task task = getTaskManagementRepository().findTaskById(Integer.parseInt(parameters.get(0)));
        Priority priority = Priority.valueOf(parameters.get(1));
        getTaskManagementRepository().changeStoryPriority(task, priority);

        return String.format("Story priority on %s was changed on %s",task.getTitle(), priority);
    }
}
