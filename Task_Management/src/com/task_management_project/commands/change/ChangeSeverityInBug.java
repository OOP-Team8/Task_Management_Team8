package com.task_management_project.commands.change;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.TaskManagementRepositoryImpl;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Bug;
import com.task_management_project.models.contracts.Story;
import com.task_management_project.models.enums.BugSeverity;
import com.task_management_project.models.enums.Priority;
import com.task_management_project.utils.ParsingHelpers;
import com.task_management_project.utils.Validation;

import javax.print.attribute.standard.Severity;
import java.util.List;

public class ChangeSeverityInBug extends BaseCommand {
    private final int EXPECTED_PARAMS = 2;
    private final String ERROR_MESSAGE = "Wrong Id";

    public ChangeSeverityInBug(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);

        Bug bug = TaskManagementRepositoryImpl.findById(ParsingHelpers.tryParseInt(parameters.get(0),ERROR_MESSAGE)
                ,getTaskManagementRepository().getBugs());
        BugSeverity severity = BugSeverity.valueOf(parameters.get(1));
        getTaskManagementRepository().changeBugSeverity(bug, severity);

        return String.format("Bug severity on %s was changed to %s",bug.getTitle(), severity);
    }
}
