package com.task_management_project.commands.show;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Board;
import com.task_management_project.models.contracts.Team;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ShowAllTeams extends BaseCommand {
    public final int EXPECTED_PARAMS = 0;
    public ShowAllTeams(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);
        StringBuilder builder = new StringBuilder("--TEAMS--" + System.lineSeparator());
        for (Team team : getTaskManagementRepository().getTeams()) {
            builder.append(getTaskManagementRepository().getTeams().indexOf(team)+1 + ". " + team.getName() +
                    ((getTaskManagementRepository().getTeams().indexOf(team)+1 < getTaskManagementRepository().getTeams().size()) ? System.lineSeparator() : ""));
        }
        return builder.toString();
    }
}
