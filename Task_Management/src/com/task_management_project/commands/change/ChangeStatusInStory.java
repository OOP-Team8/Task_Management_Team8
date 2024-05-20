package com.task_management_project.commands.change;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.StoryStatus;
import com.task_management_project.utils.ParsingHelpers;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ChangeStatusInStory extends BaseCommand {
    private final int EXPECTED_PARAMS = 2;
    private final String ERROR_MESSAGE = "Wrong Id";

    public ChangeStatusInStory(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);

        Story story = TaskManagementRepositoryImpl.findById(ParsingHelpers.tryParseInt(parameters.get(0)
                ,ERROR_MESSAGE),getTaskManagementRepository().getStories());
        StoryStatus status = StoryStatus.valueOf(parameters.get(1));
        getTaskManagementRepository().changeStoryStatus(story, status);

        return String.format("Story status on %s was changed to %s",story.getTitle(), status);
    }
}
