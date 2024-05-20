package com.task_management_project.commands.change;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.contracts.Task;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.utils.ParsingHelpers;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ChangePriorityInStory extends BaseCommand {
    private final int EXPECTED_PARAMS = 2;
    private final String ERROR_MESSAGE = "Wrong Id";
    public ChangePriorityInStory(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);

        Story story = TaskManagementRepositoryImpl.findById(ParsingHelpers.tryParseInt(parameters.get(0),ERROR_MESSAGE),getTaskManagementRepository().getStories());
        Priority priority = Priority.valueOf(parameters.get(1));
        getTaskManagementRepository().changeStoryPriority(story, priority);

        return String.format("Story priority on %s was changed to %s",story.getTitle(), priority);
    }
}
