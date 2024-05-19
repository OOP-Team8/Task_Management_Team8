package com.task_management_project.commands.show;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.PersonImpl;
import com.task_management_project.models.contracts.Person;
import com.task_management_project.models.contracts.Team;
import com.task_management_project.utils.Validation;

import java.util.List;

public class ShowAllTeamMembers extends BaseCommand {
    public final int EXPECTED_PARAMS = 1;

    public ShowAllTeamMembers(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_PARAMS);
        StringBuilder builder = new StringBuilder("--TEAM-MEMBERS--" + System.lineSeparator());
        Team team = getTaskManagementRepository().findTeamByName(parameters.get(0));
        for (int i = 0; i < team.getMembers().size(); i++) {
            builder.append(i + 1).append(". ").append(team.getMembers().get(i).getName()).append(
                    ((team.getMembers().indexOf(team.getMembers().get(i))+1 < team.getMembers().size() || getTaskManagementRepository().getTeams().indexOf(team)+1 < getTaskManagementRepository().getTeams().size()) ? System.lineSeparator() : ""));
        }
        return builder.toString();
    }
}
