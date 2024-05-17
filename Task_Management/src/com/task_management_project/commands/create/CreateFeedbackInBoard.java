package com.task_management_project.commands.create;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Board;
import com.task_management_project.models.contracts.Feedback;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.enums.FeedbackStatus;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.models.enums.Size;
import com.task_management_project.models.enums.StoryStatus;
import com.task_management_project.utils.ParsingHelpers;
import com.task_management_project.utils.Validation;

import java.util.List;

public class CreateFeedbackInBoard extends BaseCommand {
    private static final String INVALID_RATING = "Invalid rating";

    private final static String FEEDBACK_ADDED_SUCCESSFULLY = "Feedback %s added to board %s successfully!";
    public final int EXPECTED_PARAMS = 5;
    public CreateFeedbackInBoard(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        FeedbackStatus feedbackStatus = ParsingHelpers.tryParseEnum(parameters.get(2),FeedbackStatus.class);
        int rating = ParsingHelpers.tryParseInt(parameters.get(3),INVALID_RATING);
        String boardName = parameters.get(4);

        return createFeedback(title,description,feedbackStatus,rating,boardName);
    }

    private String createFeedback(String title, String description, FeedbackStatus feedbackStatus, int rating,String boardName){
        Feedback feedback = getTaskManagementRepository().createFeedback(title,description,feedbackStatus,rating);
        Board board = getTaskManagementRepository().findBoardByName(boardName);
        getTaskManagementRepository().addTaskToBoard(feedback,board);

        return String.format(FEEDBACK_ADDED_SUCCESSFULLY,title,boardName);
    }
}
