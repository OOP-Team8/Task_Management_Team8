package com.task_management_project.commands.change;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Feedback;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.enums.FeedbackStatus;
import com.task_management_project.models.enums.StoryStatus;
import com.task_management_project.utils.ParsingHelpers;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ChangeStatusInFeedback extends BaseCommand {
    private final int EXPECTED_PARAMS = 2;
    private final String ERROR_MESSAGE = "Wrong Id";

    public ChangeStatusInFeedback(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);

        Feedback feedback = TaskManagementRepositoryImpl.findById(ParsingHelpers.tryParseInt(parameters.get(0)
                ,ERROR_MESSAGE),getTaskManagementRepository().getFeedbacks());
        FeedbackStatus status = FeedbackStatus.valueOf(parameters.get(1));
        getTaskManagementRepository().changeFeedbackStatus(feedback, status);

        return String.format("Feedback status on %s was changed to %s",feedback.getTitle(), status);
    }
}
