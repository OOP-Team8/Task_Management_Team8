package com.task_management_project.commands.show;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Team;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ShowAllTeamBoards extends BaseCommand {
    public final int EXPECTED_PARAMS = 0;
    public ShowAllTeamBoards(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);
        StringBuilder builder = new StringBuilder("--BOARDS--" + System.lineSeparator());
        for (Team team : getTaskManagementRepository().getTeams()) {
            builder.append(String.format("--%s--", team.getName())).append(System.lineSeparator());
            for (int i = 0; i < team.getBoards().size(); i++) {
                builder.append(i + 1).append(". ").append(team.getBoards().get(i).getName()).append(
                        ((team.getBoards().indexOf(team.getBoards().get(i))+1 < team.getBoards().size() || getTaskManagementRepository().getTeams().indexOf(team)+1 < getTaskManagementRepository().getTeams().size()) ? System.lineSeparator() : ""));
            }
        }
        return builder.toString();
    }
}
