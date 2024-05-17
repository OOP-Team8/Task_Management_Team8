package com.task_management_project.commands.create;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.PersonImpl;
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

    private final static String BUG_ADDED_SUCCESSFULLY = "%s added bug successfully!";
    private final int EXPECTED_PARAMS = 6;
    public CreateBugInBoard(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        BugStatus bugStatus = ParsingHelpers.tryParseEnum(parameters.get(2),BugStatus.class);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(3),Priority.class);
        BugSeverity bugSeverity = ParsingHelpers.tryParseEnum(parameters.get(4),BugSeverity.class);
        Person person = getTaskManagementRepository().findPersonByName(parameters.get(5));


        return createBug(title, description,priority, bugStatus, bugSeverity,person);
    }



    private String createBug( String title, String description,  Priority priority,BugStatus bugStatus, BugSeverity bugSeverity,Person person){
        Bug bug = getTaskManagementRepository().createBug(title, description, priority, bugStatus, bugSeverity,person);

        getTaskManagementRepository().getBugs().add(bug);

        return String.format(BUG_ADDED_SUCCESSFULLY, getTaskManagementRepository().findBugByTitle(title));
    }
}
