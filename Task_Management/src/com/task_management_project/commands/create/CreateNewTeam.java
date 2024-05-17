package com.task_management_project.commands.create;

import com.task_management_project.commands.BaseCommand;
import com.task_management_project.core.contracts.TaskManagementRepository;
import com.task_management_project.models.contracts.Team;
import com.task_management_project.utils.Validation;

import java.util.List;

public class CreateNewTeam extends BaseCommand {
    public final int EXPECTED_PARAMS = 1;

    private final static String TEAM_CREATED_SUCCESSFULLY = "Team %s created successfully!";

    public CreateNewTeam(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_PARAMS);

        String name = parameters.get(0);

        return createTeam(name);
    }

    public String createTeam(String name){
        Team team = getTaskManagementRepository().createTeam(name);

        getTaskManagementRepository().addTeam(team);

        return String.format(TEAM_CREATED_SUCCESSFULLY,name);
    }
}
