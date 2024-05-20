package com.task_management_project.commands.change;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.Size;
import com.task_management_project.utils.ParsingHelpers;
import com.task_management_project.utils.Validation;

import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class ChangeSizeInStory extends BaseCommand {
    private final int EXPECTED_PARAMS = 2;
    private final String ERROR_MESSAGE = "Wrong Id";
    public ChangeSizeInStory(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_PARAMS);

        Story story = TaskManagementRepositoryImpl.findById(ParsingHelpers.tryParseInt(parameters.get(0)
                ,ERROR_MESSAGE),getTaskManagementRepository().getStories());
        Size size = Size.valueOf(parameters.get(1).toUpperCase());
        getTaskManagementRepository().changeStorySize(story, size);

        return String.format("Story size on %s was changed to %s", story.getTitle(), size);
    }
}
