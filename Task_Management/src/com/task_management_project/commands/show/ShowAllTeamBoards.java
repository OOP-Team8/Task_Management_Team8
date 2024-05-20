package com.task_management_project.commands.show;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Team;
import com.task_management_project.utils.ListingHelpers;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ShowAllTeamBoards extends BaseCommand {
    public final int EXPECTED_PARAMS = 1;
    public ShowAllTeamBoards(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);
        Team team = getTaskManagementRepository().findTeamByName(parameters.get(0));
        StringBuilder builder = new StringBuilder(String.format("--%s--",team.getName()) + System.lineSeparator());
        builder.append(ListingHelpers.elementsToString(team.getBoards()));
        return builder.toString();
    }
}
