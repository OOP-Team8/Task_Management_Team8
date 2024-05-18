package com.task_management_project.commands.create;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.PersonImpl;
import com.task_management_project.models.contracts.Board;
import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.contracts.Feedback;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.enums.BugSeverity;
import com.task_management_project.models.enums.BugStatus;
import com.task_management_project.models.enums.FeedbackStatus;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.utils.ParsingHelpers;
import com.task_management_project.utils.Validation;

import java.util.List;

public class CreateBugInBoard extends BaseCommand {

    private final static String BUG_ADDED_SUCCESSFULLY = "Bug %s added to board %s successfully!";
    private final int EXPECTED_PARAMS = 7;

    public CreateBugInBoard(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(2),Priority.class);
        BugStatus bugStatus = ParsingHelpers.tryParseEnum(parameters.get(3),BugStatus.class);
        BugSeverity bugSeverity = ParsingHelpers.tryParseEnum(parameters.get(4),BugSeverity.class);
        Person person = getTaskManagementRepository().findPersonByName(parameters.get(5));
        String boardName = parameters.get(6);

        return createBug(title, description, priority, bugStatus, bugSeverity, person, boardName);
    }

    private String createBug(String title, String description,  Priority priority,BugStatus bugStatus, BugSeverity bugSeverity,Person person, String boardName){
        Bug bug = getTaskManagementRepository().createBug(title, description, priority, bugStatus, bugSeverity,person);
        Board board = getTaskManagementRepository().findBoardByName(boardName);

        getTaskManagementRepository().addTaskToBoard(bug,board);
        getTaskManagementRepository().addTaskToMember(bug, person);

        return String.format(BUG_ADDED_SUCCESSFULLY,title,boardName);
    }
}
